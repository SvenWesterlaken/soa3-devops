package soa.svenwstrl

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.management.Sprint
import soa.svenwstrl.management.states.sprint.*
import soa.svenwstrl.users.TeamMember
import java.io.File
import java.util.*



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SprintTest {
    private val pipeline: Pipeline = mockk(relaxed = true)

    @Nested
    inner class `Changing Properties` {
        val currentDate = Date()
        val newDate = Date(9999999999999999)

        @Test
        fun `Updates possible`() {
            val sprint = Sprint(Sprint.SprintType.RELEASE, "TestSprint", currentDate, currentDate, pipeline)

            sprint.setStartDate(newDate)
            sprint.setEndDate(newDate)
            sprint.setName("NewTestSprint")

            assertThat(sprint.getName()).isEqualTo("NewTestSprint").isNotEqualTo("TestSprint")
            assertThat(sprint.getEndDate()).isEqualTo(newDate).isNotEqualTo(currentDate)
            assertThat(sprint.getStartDate()).isEqualTo(newDate).isNotEqualTo(currentDate)
        }

        @Test
        fun `Updates not possible during Pipeline State`() {


            val sprint = Sprint(Sprint.SprintType.RELEASE, "TestSprint", currentDate, currentDate, pipeline)
            sprint.setState(PipelineState(sprint))

            val setStartDate = catchThrowable{ sprint.setStartDate(newDate) }
            val setEndDate = catchThrowable{ sprint.setEndDate(newDate) }
            val setName = catchThrowable { sprint.setName("NewTestSprint") }

            assertThat(setStartDate).isInstanceOf(NotImplementedError::class.java)
            assertThat(setEndDate).isInstanceOf(NotImplementedError::class.java)
            assertThat(setName).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun `Updates not possible during Executed State`() {

            val sprint = Sprint(Sprint.SprintType.RELEASE, "TestSprint", currentDate, currentDate, pipeline)
            sprint.setState(ExecutedState(sprint))

            val setStartDate = catchThrowable{ sprint.setStartDate(newDate) }
            val setEndDate = catchThrowable{ sprint.setEndDate(newDate) }
            val setName = catchThrowable { sprint.setName("NewTestSprint") }

            assertThat(setStartDate).isInstanceOf(NotImplementedError::class.java)
            assertThat(setEndDate).isInstanceOf(NotImplementedError::class.java)
            assertThat(setName).isInstanceOf(NotImplementedError::class.java)
        }

    }

    @Nested
    inner class `Closing sprint` {

        @Test
        fun `Upload summary (Review)`() {
            val summary = mockk<File>(relaxed = true)

            val sprint = Sprint(Sprint.SprintType.REVIEW, "TestSprint", Date(), Date(), pipeline)
            sprint.setState(ReviewState(sprint))

            sprint.closeSprint(summary)

            assertThat(sprint.getReviewSummary()).isNotNull()
            assertThat(sprint.getStateType()).isEqualTo(SprintState.Type.CLOSED)
        }

        @Test
        fun `Close sprint (Release)`() {
            val sprint = Sprint(Sprint.SprintType.REVIEW, "TestSprint", Date(), Date(), pipeline)
            sprint.setState(ReleasedState(sprint))

            sprint.closeSprint()

            assertThat(sprint.getReviewSummary()).isNull()
            assertThat(sprint.getStateType()).isEqualTo(SprintState.Type.CLOSED)
        }

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `State Transitions`() {
        private lateinit var sprint: Sprint

        @BeforeEach
        fun createSprint() {
            this.sprint = Sprint(Sprint.SprintType.REVIEW, "TestSprint", Date(), Date(), pipeline)
        }

        @Test
        fun getStateType() {
            assertThat(this.sprint.getStateType()).isEqualTo(SprintState.Type.CREATED)
        }

        @Test
        fun execute() {
            val spyState = spyk(CreatedState(sprint))

            this.sprint.setState(spyState)
            Assertions.catchThrowable { sprint.execute() }

            assertThat(verify(exactly = 1) { spyState.execute() })
            confirmVerified()
        }

        @Test
        fun finish() {
            val spyState = spyk(CreatedState(sprint))

            this.sprint.setState(spyState)
            Assertions.catchThrowable { sprint.finish() }

            assertThat(verify(exactly = 1) { spyState.finish() })
            confirmVerified()
        }

        @Test
        fun startPipeline() {
            val spyState = spyk(CreatedState(sprint))

            this.sprint.setState(spyState)
            Assertions.catchThrowable { sprint.startPipeline() }

            assertThat(verify(exactly = 1) { spyState.startPipeline() })
            confirmVerified()
        }

        @Test
        fun cancel() {
            val spyState = spyk(CreatedState(sprint))

            this.sprint.setState(spyState)
            Assertions.catchThrowable { sprint.cancel() }

            assertThat(verify(exactly = 1) { spyState.cancel() })
            confirmVerified()
        }

        @Test
        fun release() {
            val spyState = spyk(CreatedState(sprint))

            this.sprint.setState(spyState)
            Assertions.catchThrowable { sprint.release() }

            assertThat(verify(exactly = 1) { spyState.release() })
            confirmVerified()
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `Associated Member Management`() {
        private lateinit var sprint: Sprint

        @BeforeEach
        fun createSprint() {
            this.sprint = Sprint(Sprint.SprintType.REVIEW, "TestSprint", Date(), Date(), pipeline)
        }

        @Test
        fun `Add & remove member`() {
            val member = mockk<TeamMember>()

            this.sprint.addTeamMember(member)

            assertThat(sprint.getTeamMembers()).isNotEmpty()
            assertThat(sprint.getTeamMembers()).hasSize(1)

            this.sprint.removeTeamMember(member)

            assertThat(sprint.getTeamMembers()).isEmpty()
            assertThat(sprint.getTeamMembers()).hasSize(0)
        }

    }







}
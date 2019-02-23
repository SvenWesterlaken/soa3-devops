package soa.svenwstrl

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.management.Sprint
import soa.svenwstrl.management.states.sprint.ExecutedState
import soa.svenwstrl.management.states.sprint.PipelineState
import soa.svenwstrl.management.states.sprint.SprintState
import java.util.*



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SprintTest {

    @Test
    fun Notifications() {

        val pipeline = spyk(Pipeline())

        val sprint = Sprint(Sprint.SprintType.RELEASE, "TestSprint", Date(), Date(), pipeline)

        sprint.setState(ExecutedState(sprint))
        sprint.finish()
        sprint.startPipeline()

        verify(exactly = 1) { pipeline.submit(true) }

        confirmVerified()

        assertThat(pipeline.hasSubscribers())
        assertThat(sprint.getStateType()).isEqualTo(SprintState.Type.RELEASED)

    }

    @Nested
    inner class `Changing Properties` {
        val currentDate = Date()
        val newDate = Date(9999999999999999)
        private val pipeline: Pipeline = mockk(relaxed = true)

        @Test
        fun `Updates possible`() {
            val sprint = Sprint(Sprint.SprintType.RELEASE, "TestSprint", currentDate, currentDate, pipeline)

            sprint.setStartDate(newDate)
            sprint.setEndDate(newDate)
            sprint.setName("NewTestSprint")

            assertThat(sprint.getName()).isEqualTo("NewTestSprint").isNotEqualTo("TestSprint")
            assertThat(sprint.getEndDate()).isEqualTo(newDate).isNotEqualTo(currentDate)
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



}
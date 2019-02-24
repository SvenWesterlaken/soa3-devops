package soa.svenwstrl

import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.management.Sprint
import soa.svenwstrl.management.states.sprint.*
import java.io.File
import java.util.*



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SprintTest {

//    @Test
//    fun Notifications() {
//
//        val pipeline = spyk(Pipeline())
//
//        val sprint = Sprint(Sprint.SprintType.RELEASE, "TestSprint", Date(), Date(), pipeline)
//
//        sprint.setState(ExecutedState(sprint))
//        sprint.finish()
//        sprint.startPipeline()
//
//        verify(exactly = 1) { pipeline.submit(true) }
//
//        confirmVerified()
//
//        assertThat(pipeline.hasSubscribers())
//        assertThat(sprint.getStateType()).isEqualTo(SprintState.Type.RELEASED)
//
//    }

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
        private val pipeline: Pipeline = mockk(relaxed = true)

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







}
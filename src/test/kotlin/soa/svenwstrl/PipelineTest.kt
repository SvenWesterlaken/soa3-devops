package soa.svenwstrl

import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PipelineTest {

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

}
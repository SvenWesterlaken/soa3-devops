package soa.svenwstrl

import io.mockk.confirmVerified
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.management.Sprint
import soa.svenwstrl.management.states.sprint.ExecutedState
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

}
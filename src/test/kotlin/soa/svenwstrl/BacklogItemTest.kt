package soa.svenwstrl

import io.mockk.confirmVerified
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.management.BacklogItem
import soa.svenwstrl.management.Sprint
import soa.svenwstrl.management.states.backlogitem.DoingState
import soa.svenwstrl.management.states.sprint.ExecutedState
import soa.svenwstrl.management.states.sprint.SprintState
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BacklogItemTest {
    @Test
    fun Notifications() {

        val backLogItem = BacklogItem(BacklogItem.BacklogItemType.TODO)

        backLogItem.setState(DoingState(backLogItem))
        backLogItem.review()

        Assertions.assertThat(pipeline.hasSubscribers())
        Assertions.assertThat(sprint.getStateType()).isEqualTo(SprintState.Type.RELEASED)

    }
}
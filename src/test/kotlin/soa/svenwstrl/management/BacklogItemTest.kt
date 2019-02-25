package soa.svenwstrl.management

import io.mockk.confirmVerified
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.management.states.backlogitem.BacklogItemState
import soa.svenwstrl.management.states.backlogitem.ToDoState

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BacklogItemTest {
    private lateinit var backlogItem: BacklogItem

    @BeforeEach
    fun refreshBacklogItem() {
        this.backlogItem = BacklogItem(BacklogItem.BacklogItemType.TODO)
    }

    @Test
    fun `Add & remove an activity`() {
        val activity = BacklogItemActivity("test")

        this.backlogItem.addActivity(activity)

        Assertions.assertThat(backlogItem.getActivity(0)).isNotNull
        Assertions.assertThat(backlogItem.getActivities()).isNotEmpty
        Assertions.assertThat(backlogItem.getActivities()).hasSize(1)

        this.backlogItem.removeActivity(activity)

        Assertions.assertThat(backlogItem.getActivities()).isEmpty()
        Assertions.assertThat(backlogItem.getActivities()).hasSize(0)

        Assertions.assertThat(Assertions.catchThrowable { backlogItem.getActivity(0) })
            .isInstanceOf(IndexOutOfBoundsException::class.java)
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `State Transitions`() {
        private lateinit var backlogItem: BacklogItem

        @BeforeEach
        fun createBacklogItem() {
            this.backlogItem = BacklogItem(BacklogItem.BacklogItemType.TODO)
        }

        @Test
        fun getStateType() {
            Assertions.assertThat(this.backlogItem.getStateType()).isEqualTo(BacklogItemState.Type.TODO)
        }

        @Test
        fun setDoing() {
            val spyState = spyk(ToDoState(backlogItem))

            this.backlogItem.setState(spyState)
            Assertions.catchThrowable { backlogItem.setDoing() }

            verify(exactly = 1) { spyState.setDoing() }
            confirmVerified()
            Assertions.assertThat(true).isTrue()

        }

        @Test
        fun review() {
            val spyState = spyk(ToDoState(backlogItem))

            this.backlogItem.setState(spyState)
            Assertions.catchThrowable { backlogItem.review() }

            verify(exactly = 1) { spyState.setReview() }
            confirmVerified()
            Assertions.assertThat(true).isTrue()
        }

        @Test
        fun setDone() {
            val spyState = spyk(ToDoState(backlogItem))

            this.backlogItem.setState(spyState)
            Assertions.catchThrowable { backlogItem.setDone() }

            verify(exactly = 1) { spyState.setDone() }
            confirmVerified()
            Assertions.assertThat(true).isTrue()
        }
    }

}
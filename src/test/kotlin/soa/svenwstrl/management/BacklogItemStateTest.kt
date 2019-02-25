package soa.svenwstrl.management

import io.mockk.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.management.states.backlogitem.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BacklogItemStateTest {

    private val backlogItem =  mockk<BacklogItem>(relaxed = true)

    @Nested
    inner class `ToDo State` {
        private val state = ToDoState(backlogItem)

        @Test
        fun stateType() {
            Assertions.assertThat(state.getState()).isEqualTo(BacklogItemState.Type.TODO)
        }

        @Test
        fun setToDo() {
            assertThat(Assertions.catchThrowable { state.setToDo() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun setDoing() {
            Assertions.assertThat(Assertions.catchThrowable { state.setDoing() }).doesNotThrowAnyException()
        }

        @Test
        fun review() {
            Assertions.assertThat(Assertions.catchThrowable { state.setReview() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun setDone() {
            Assertions.assertThat(Assertions.catchThrowable { state.setDone() }).isInstanceOf(NotImplementedError::class.java)
        }
    }

    @Nested
    inner class `Doing State` {
        private val state = DoingState(backlogItem)

        @Test
        fun stateType() {
            assertThat(state.getState()).isEqualTo(BacklogItemState.Type.DOING)
        }

        @Test
        fun setToDo() {
            assertThat(Assertions.catchThrowable { state.setToDo() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun setDoing() {
            assertThat(Assertions.catchThrowable { state.setDoing() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { state.setReview() }).doesNotThrowAnyException()
        }

        @Test
        fun setDone() {
            assertThat(Assertions.catchThrowable { state.setDone() }).isInstanceOf(NotImplementedError::class.java)
        }

    }

    @Nested
    inner class `Review State` {
        private val state = ReviewState(backlogItem)

        @Test
        fun stateType() {
            assertThat(state.getState()).isEqualTo(BacklogItemState.Type.REVIEW)
        }

        @Test
        fun setToDo() {
            assertThat(Assertions.catchThrowable { state.setToDo() }).doesNotThrowAnyException()
        }

        @Test
        fun setDoing() {
            assertThat(Assertions.catchThrowable { state.setDoing() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { state.setReview() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun setDone() {
            assertThat(Assertions.catchThrowable { state.setDone() }).doesNotThrowAnyException()
        }

    }

    @Nested
    inner class `Done State` {
        private val state = DoneState(backlogItem)

        @Test
        fun stateType() {
            assertThat(state.getState()).isEqualTo(BacklogItemState.Type.DONE)
        }

        @Test
        fun setToDo() {
            assertThat(Assertions.catchThrowable { state.setToDo() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun setDoing() {
            assertThat(Assertions.catchThrowable { state.setDoing() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { state.setReview() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun setDone() {
            assertThat(Assertions.catchThrowable { state.setDone() }).isInstanceOf(NotImplementedError::class.java)
        }

    }



}
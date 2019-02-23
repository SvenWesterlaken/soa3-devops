package soa.svenwstrl

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.management.Sprint
import soa.svenwstrl.management.states.sprint.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SprintStateTest {
    private val sprint =  mockk<Sprint>(relaxed = true)

    @Nested
    inner class `Created State` {
        private val state = CreatedState(sprint)

        @Test
        fun stateType() {
            assertThat(state.getState()).isEqualTo(SprintState.Type.CREATED)
        }

        @Test
        fun execute() {
            assertThat(Assertions.catchThrowable { state.execute() }).doesNotThrowAnyException()
        }

        @Test
        fun finish() {
            assertThat(Assertions.catchThrowable { state.finish() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun startPipeline() {
            assertThat(Assertions.catchThrowable { state.startPipeline() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun cancel() {
            assertThat(Assertions.catchThrowable { state.cancel() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { state.review() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun release() {
            assertThat(Assertions.catchThrowable { state.release() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun close() {
            assertThat(Assertions.catchThrowable { state.close() }).isInstanceOf(NotImplementedError::class.java)
        }

    }

    @Nested
    inner class `Executed State` {
        private val state = ExecutedState(sprint)

        @Test
        fun stateType() {
            assertThat(state.getState()).isEqualTo(SprintState.Type.EXECUTED)
        }

        @Test
        fun execute() {
            assertThat(Assertions.catchThrowable { state.execute() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun finish() {
            assertThat(Assertions.catchThrowable { state.finish() }).doesNotThrowAnyException()
        }

        @Test
        fun startPipeline() {
            assertThat(Assertions.catchThrowable { state.startPipeline() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun cancel() {
            assertThat(Assertions.catchThrowable { state.cancel() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { state.review() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun release() {
            assertThat(Assertions.catchThrowable { state.release() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun close() {
            assertThat(Assertions.catchThrowable { state.close() }).isInstanceOf(NotImplementedError::class.java)
        }

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `Finished State` {
        private lateinit var stateRelease: FinishedState
        private lateinit var stateReview: FinishedState

        @BeforeEach
        fun init() {
            val sprintRelease = mockk<Sprint>(relaxed = true)

            every {sprintRelease.getSprintType()} returns Sprint.SprintType.RELEASE

            val sprintReview = mockk<Sprint>(relaxed = true)

            every {sprintReview.getSprintType()} returns Sprint.SprintType.REVIEW

            this.stateRelease = FinishedState(sprintRelease)
            this.stateReview = FinishedState(sprintReview)
        }

        @Test
        fun stateType() {
            assertThat(stateRelease.getState()).isEqualTo(SprintState.Type.FINISHED)
            assertThat(stateReview.getState()).isEqualTo(SprintState.Type.FINISHED)
        }

        @Test
        fun execute() {
            assertThat(Assertions.catchThrowable { stateRelease.execute() }).isInstanceOf(NotImplementedError::class.java)
            assertThat(Assertions.catchThrowable { stateReview.execute() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun finish() {
            assertThat(Assertions.catchThrowable { stateRelease.finish() }).isInstanceOf(NotImplementedError::class.java)
            assertThat(Assertions.catchThrowable { stateReview.finish() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun startPipeline() {
            assertThat(Assertions.catchThrowable { stateRelease.startPipeline() }).doesNotThrowAnyException()
            assertThat(Assertions.catchThrowable { stateReview.startPipeline() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun cancel() {
            assertThat(Assertions.catchThrowable { stateRelease.cancel() }).doesNotThrowAnyException()
            assertThat(Assertions.catchThrowable { stateReview.cancel() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { stateRelease.review() }).isInstanceOf(NotImplementedError::class.java)
            assertThat(Assertions.catchThrowable { stateReview.review() }).doesNotThrowAnyException()
        }

        @Test
        fun release() {
            assertThat(Assertions.catchThrowable { stateRelease.release() }).isInstanceOf(NotImplementedError::class.java)
            assertThat(Assertions.catchThrowable { stateReview.release() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun close() {
            assertThat(Assertions.catchThrowable { stateRelease.close() }).isInstanceOf(NotImplementedError::class.java)
            assertThat(Assertions.catchThrowable { stateReview.close() }).isInstanceOf(NotImplementedError::class.java)
        }

    }

    @Nested
    inner class `Pipeline State` {
        private val state = PipelineState(sprint)

        @Test
        fun stateType() {
            assertThat(state.getState()).isEqualTo(SprintState.Type.PIPELINE)
        }

        @Test
        fun execute() {
            assertThat(Assertions.catchThrowable { state.execute() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun finish() {
            assertThat(Assertions.catchThrowable { state.finish() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun startPipeline() {
            assertThat(Assertions.catchThrowable { state.startPipeline() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun cancel() {
            assertThat(Assertions.catchThrowable { state.cancel() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { state.review() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun release() {
            assertThat(Assertions.catchThrowable { state.release() }).doesNotThrowAnyException()
        }

        @Test
        fun close() {
            assertThat(Assertions.catchThrowable { state.close() }).isInstanceOf(NotImplementedError::class.java)
        }

    }

    @Nested
    inner class `Released State` {
        private val state = ReleasedState(sprint)

        @Test
        fun stateType() {
            assertThat(state.getState()).isEqualTo(SprintState.Type.RELEASED)
        }

        @Test
        fun execute() {
            assertThat(Assertions.catchThrowable { state.execute() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun finish() {
            assertThat(Assertions.catchThrowable { state.finish() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun startPipeline() {
            assertThat(Assertions.catchThrowable { state.startPipeline() }).doesNotThrowAnyException()
        }

        @Test
        fun cancel() {
            assertThat(Assertions.catchThrowable { state.cancel() }).doesNotThrowAnyException()
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { state.review() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun release() {
            assertThat(Assertions.catchThrowable { state.release() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun close() {
            assertThat(Assertions.catchThrowable { state.close() }).doesNotThrowAnyException()
        }

    }

    @Nested
    inner class `Review State` {
        private val state = ReviewState(sprint)

        @Test
        fun stateType() {
            assertThat(state.getState()).isEqualTo(SprintState.Type.REVIEW)
        }

        @Test
        fun execute() {
            assertThat(Assertions.catchThrowable { state.execute() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun finish() {
            assertThat(Assertions.catchThrowable { state.finish() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun startPipeline() {
            assertThat(Assertions.catchThrowable { state.startPipeline() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun cancel() {
            assertThat(Assertions.catchThrowable { state.cancel() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { state.review() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun release() {
            assertThat(Assertions.catchThrowable { state.release() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun close() {
            assertThat(Assertions.catchThrowable { state.close() }).doesNotThrowAnyException()
        }

    }

    @Nested
    inner class `Closed State` {
        private val state = ClosedState(sprint)

        @Test
        fun stateType() {
            assertThat(state.getState()).isEqualTo(SprintState.Type.CLOSED)
        }

        @Test
        fun execute() {
            assertThat(Assertions.catchThrowable { state.execute() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun finish() {
            assertThat(Assertions.catchThrowable { state.finish() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun startPipeline() {
            assertThat(Assertions.catchThrowable { state.startPipeline() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun cancel() {
            assertThat(Assertions.catchThrowable { state.cancel() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { state.review() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun release() {
            assertThat(Assertions.catchThrowable { state.release() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun close() {
            assertThat(Assertions.catchThrowable { state.close() }).isInstanceOf(NotImplementedError::class.java)
        }

    }

    @Nested
    inner class `Canceled State` {
        private val state = CanceledState(sprint)

        @Test
        fun stateType() {
            assertThat(state.getState()).isEqualTo(SprintState.Type.CANCELED)
        }

        @Test
        fun execute() {
            assertThat(Assertions.catchThrowable { state.execute() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun finish() {
            assertThat(Assertions.catchThrowable { state.finish() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun startPipeline() {
            assertThat(Assertions.catchThrowable { state.startPipeline() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun cancel() {
            assertThat(Assertions.catchThrowable { state.cancel() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun review() {
            assertThat(Assertions.catchThrowable { state.review() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun release() {
            assertThat(Assertions.catchThrowable { state.release() }).isInstanceOf(NotImplementedError::class.java)
        }

        @Test
        fun close() {
            assertThat(Assertions.catchThrowable { state.close() }).isInstanceOf(NotImplementedError::class.java)
        }

    }



}
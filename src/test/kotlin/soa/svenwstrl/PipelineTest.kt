package soa.svenwstrl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.devops.PipelineAction

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PipelineTest {
    private lateinit var pipeline: Pipeline
    private lateinit var action: PipelineAction

    @BeforeEach
    fun refreshPipeline() {
        this.pipeline = Pipeline()
        this.action= mockk<PipelineAction>(relaxed = true)
    }

    @Test
    fun `Execution Successful`() {
        this.pipeline.addAction(action)

        val executionSucceeded = pipeline.execute()

        verify(exactly = 1) { action.execute() }

        assertThat(pipeline.getActions()).hasSize(1)
        assertThat(pipeline.getActions()).isNotEmpty()
        assertThat(executionSucceeded).isTrue()
        assertThat(pipeline.succeeded())
        assertThat(pipeline.getError()).isNull()
        assertThat(pipeline.isRunning()).isFalse()

    }

    @Test
    fun `Execution Failed`() {
        every { action.execute() } throws Exception()

        this.pipeline.addAction(action)

        val executionSucceeded = pipeline.execute()

        verify(exactly = 1) { action.execute() }

        assertThat(pipeline.getActions()).hasSize(1)
        assertThat(pipeline.getActions()).isNotEmpty()
        assertThat(executionSucceeded).isFalse()
        assertThat(pipeline.succeeded())
        assertThat(pipeline.getError()).isNotNull()
        assertThat(pipeline.isRunning()).isFalse()
    }

    @Test
    fun `Add & Remove action`() {

        this.pipeline.addAction(action)

        assertThat(pipeline.getActions()).hasSize(1)
        assertThat(pipeline.getActions()).isNotEmpty()
        assertThat(pipeline.getAction(0)).isEqualTo(action)

        this.pipeline.removeAction(action)


        assertThat(pipeline.getActions()).hasSize(0)
        assertThat(pipeline.getActions()).isEmpty()
        assertThat(Assertions.catchThrowable { pipeline.getAction(0) }).isInstanceOf(IndexOutOfBoundsException::class.java)

    }


}
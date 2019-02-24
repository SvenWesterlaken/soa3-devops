package soa.svenwstrl

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.devops.PipelineAction

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PipelineTest {
    private lateinit var pipeline: Pipeline

    @BeforeEach
    fun refreshPipeline() {
        this.pipeline = Pipeline()
    }

    @Test
    fun `Execution Successful`() {

        val action = mockk<PipelineAction>(relaxed = true)

        this.pipeline.addAction(action)

        val executionSucceeded = pipeline.execute()

        assertThat(verify(exactly = 1) { action.execute() })
        assertThat(pipeline.getActions()).hasSize(1)
        assertThat(pipeline.getActions()).isNotEmpty()
        assertThat(executionSucceeded).isTrue()
        assertThat(pipeline.succeeded())
        assertThat(pipeline.getError()).isNull()
        assertThat(pipeline.isRunning()).isFalse()

    }

    @Test
    fun `Execution Failed`() {
        val action = mockk<PipelineAction>(relaxed = true)

        every { action.execute() } throws Exception()

        this.pipeline.addAction(action)

        val executionSucceeded = pipeline.execute()

        assertThat(verify(exactly = 1) { action.execute() })
        assertThat(pipeline.getActions()).hasSize(1)
        assertThat(pipeline.getActions()).isNotEmpty()
        assertThat(executionSucceeded).isFalse()
        assertThat(pipeline.succeeded())
        assertThat(pipeline.getError()).isNotNull()
        assertThat(pipeline.isRunning()).isFalse()
    }


}
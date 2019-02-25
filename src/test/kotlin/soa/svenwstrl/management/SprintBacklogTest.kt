package soa.svenwstrl.management

import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SprintBacklogTest {
    private lateinit var sprintBacklog: SprintBacklog

    @BeforeEach
    fun refreshSprintBacklog() {
        this.sprintBacklog = SprintBacklog()
    }

    @Test
    fun `Add & remove an item`() {
        val backlogItem = mockk<BacklogItem>(relaxed = true)

        this.sprintBacklog.addItem(backlogItem)

        assertThat(sprintBacklog.getBacklogItems()).isNotEmpty
        assertThat(sprintBacklog.getBacklogItems()).hasSize(1)
        assertThat(sprintBacklog.getBacklogItem(0)).isNotNull

        this.sprintBacklog.removeItem(backlogItem)

        assertThat(sprintBacklog.getBacklogItems()).isEmpty()
        assertThat(sprintBacklog.getBacklogItems()).hasSize(0)

        assertThat(Assertions.catchThrowable { sprintBacklog.getBacklogItem(0) }).isInstanceOf(IndexOutOfBoundsException::class.java)
    }

}
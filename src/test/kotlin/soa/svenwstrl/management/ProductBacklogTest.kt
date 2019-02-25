package soa.svenwstrl.management

import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductBacklogTest {
    private lateinit var productBacklog: ProductBacklog

    @BeforeEach
    fun refreshSprintBacklog() {
        this.productBacklog = ProductBacklog()
    }

    @Test
    fun `Add & remove an item`() {
        val backlogItem = mockk<BacklogItem>(relaxed = true)

        this.productBacklog.addItem(backlogItem)

        assertThat(productBacklog.getBacklogItems()).isNotEmpty
        assertThat(productBacklog.getBacklogItems()).hasSize(1)
        assertThat(productBacklog.getBacklogItem(0)).isNotNull

        this.productBacklog.removeItem(backlogItem)

        assertThat(productBacklog.getBacklogItems()).isEmpty()
        assertThat(productBacklog.getBacklogItems()).hasSize(0)

        assertThat(Assertions.catchThrowable { productBacklog.getBacklogItem(0) }).isInstanceOf(IndexOutOfBoundsException::class.java)
    }

}
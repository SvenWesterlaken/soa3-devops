package soa.svenwstrl.forum

import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ForumTest {
    private lateinit var forum: Forum

    @BeforeEach
    fun refreshForum() {
        this.forum = Forum()
    }

    @Test
    fun `Add & remove an item`() {
        val thread = mockk<Thread>(relaxed = true)

        this.forum.addThread(thread)

        Assertions.assertThat(forum.getThreads()).isNotEmpty
        Assertions.assertThat(forum.getThreads()).hasSize(1)
        Assertions.assertThat(forum.getThread(0)).isNotNull

        this.forum.removeThread(thread)

        Assertions.assertThat(forum.getThreads()).isEmpty()
        Assertions.assertThat(forum.getThreads()).hasSize(0)

        Assertions.assertThat(Assertions.catchThrowable { forum.getThread(0) }).isInstanceOf(IndexOutOfBoundsException::class.java)
    }
}
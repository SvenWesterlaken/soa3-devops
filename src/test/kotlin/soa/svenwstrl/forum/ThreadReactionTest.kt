package soa.svenwstrl.forum

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.users.Developer
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ThreadReactionTest {
    private lateinit var outContent: ByteArrayOutputStream
    private val originalOut = System.out

    @BeforeEach
    fun setUpStreams() {
        outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
    }

    @AfterAll
    fun restoreStreams() {
        System.setOut(originalOut)
    }


    @Test
    fun canBeEdited() {
        val parent = mockk<Thread>()

        every { parent.canBeEdited() } returns true

        val threadReaction = ThreadReaction(mockk<Developer>(), "Message", parent)

        assertThat(threadReaction.canBeEdited()).isTrue()

    }

    @Test
    fun cannotBeEdited() {
        val parent = mockk<Thread>()

        every { parent.canBeEdited() } returns false

        val threadReaction = ThreadReaction(mockk<Developer>(), "Message", parent)

        assertThat(threadReaction.canBeEdited()).isFalse()
    }

    @Test
    fun show() {
        val parent = mockk<Thread>()
        val creator = mockk<Developer>()

        every { creator.getName() } returns "TestPerson"

        val threadReaction = ThreadReaction(creator, "Message", parent)

        threadReaction.show()

        assertThat(outContent.toString()).isEqualTo("TestPerson: Message\n")
    }

}
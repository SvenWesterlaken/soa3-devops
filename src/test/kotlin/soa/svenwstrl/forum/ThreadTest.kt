package soa.svenwstrl.forum

import io.mockk.*
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import soa.svenwstrl.management.BacklogItem
import soa.svenwstrl.management.states.backlogitem.BacklogItemState
import soa.svenwstrl.notifications.NotificationHandler
import soa.svenwstrl.users.Developer
import soa.svenwstrl.users.TeamMember
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ThreadTest {
    private lateinit var backlogItem: BacklogItem
    private lateinit var thread: Thread
    private lateinit var creator: TeamMember
    private val message = "Test Message"

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

    @BeforeEach
    fun refreshValues() {

        creator = mockk<Developer>(relaxed = true)
        backlogItem = mockk(relaxed = true)

        every { creator.getName() } returns "TestPerson"

        this.thread = Thread("New Thread", backlogItem, creator, message)

    }

    @Nested
    inner class `Setters & Getters`() {

        @Test
        fun `Creator getter`() {
            assertThat(thread.getCreator()).isEqualTo(creator).isInstanceOf(Developer::class.java)
        }

        @Test
        fun `Name getter & setter`() {
            assertThat(thread.getName()).isEqualTo("New Thread")

            thread.setName("New Thread Name")

            assertThat(thread.getName()).isEqualTo("New Thread Name")
        }

        @Test
        fun `Backlog Item Getter`() {
            assertThat(thread.getBackLogItem()).isEqualTo(backlogItem)
        }

        @Test
        fun `Message Getter`() {
            assertThat(thread.getMessage()).isEqualTo("Test Message")
        }

    }

    @Test
    fun show() {

        thread.show()
        assertThat(outContent.toString()).isEqualTo("New Thread (TestPerson): Test Message\n")

    }

    @Nested
    inner class `Adding of reactions` {

        @Test
        fun `Add reaction when not closed`() {

            val subscriber = spyk(NotificationHandler())
            val reaction = mockk<ThreadReaction>(relaxed = true)

            thread.subscribe(subscriber)

            thread.add(reaction)

            verify(exactly = 1) { subscriber.onNext(thread) }
            confirmVerified()

            thread.traverse()

            assertThat(outContent.toString()).isEqualTo("New Thread (TestPerson): Test Message\n")
        }

        @Test
        fun `Add reaction when closed`() {

            val subscriber = spyk(NotificationHandler())
            val reaction = mockk<ThreadReaction>(relaxed = true)

            backlogItem = mockk(relaxed = true)

            every { backlogItem.getStateType() } returns BacklogItemState.Type.DONE

            thread = Thread("New Thread", backlogItem, creator, message)

            thread.subscribe(subscriber)

            assertThat(Assertions.catchThrowable { thread.add(reaction) }).isInstanceOf(NotImplementedError::class.java)

            verify(exactly = 0) { subscriber.onNext(thread) }
            confirmVerified()
        }

    }




}
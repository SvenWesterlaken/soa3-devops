package soa.svenwstrl

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.filter.NotFilter
import org.junit.jupiter.api.*
import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.devops.PipelineAction
import soa.svenwstrl.notifications.Notifiable
import soa.svenwstrl.notifications.NotificationHandler
import soa.svenwstrl.notifications.Notifier
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.lang.NullPointerException


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NotifierPathCoverageTest {
    lateinit var notificationHandler: NotificationHandler
    lateinit var notifiable: Notifiable
    lateinit var notifier: Notifier
    private val outContent = ByteArrayOutputStream()
    private val errContent = ByteArrayOutputStream()
    private val originalOut = System.out
    private val originalErr = System.err

    @BeforeAll
    fun setUpStreams() {
        System.setOut(PrintStream(outContent))
        System.setErr(PrintStream(errContent))
    }

    @AfterAll
    fun restoreStreams() {
        System.setOut(originalOut)
        System.setErr(originalErr)
    }

    @BeforeEach
    fun refreshNotifier() {
        this.notificationHandler =  spyk(NotificationHandler())
        this.notifiable = mockk<Notifiable>(relaxed = true)

        this.outContent.reset()
    }

    @Test
    fun typesOfNotifiableAreOfTypeEmail() {
        every { notifiable.getSelectedTypes() } returns arrayListOf(Notifiable.Type.EMAIL)


        val notificationHandler = spyk(NotificationHandler())

        notificationHandler.onNext(this.notifiable)

        assertThat(outContent.toString()).isEqualTo("TO: test@email.com | This is an email message")
    }

    @Test
    fun noTypesNotHandleMessage() {
        every { notifiable.getSelectedTypes() } returns arrayListOf()

        val notificationHandler = spyk(NotificationHandler())

        notificationHandler.onNext(this.notifiable)

        assertThat(outContent.toString()).isEqualTo("")
    }

    @Test
    fun typesOfNotifiableAreOfTypeSlack() {
        every { notifiable.getSelectedTypes() } returns arrayListOf(Notifiable.Type.SLACK)

        val notificationHandler = spyk(NotificationHandler())

        notificationHandler.onNext(this.notifiable)

        assertThat(outContent.toString()).isEqualTo("TOKEN: 8a0d7fd810 | This is an slack message")
    }

    @Test
    fun typesOfNotifiableUnrecognized() {
        every { notifiable.getSelectedTypes() } returns arrayListOf(Notifiable.Type.FAX)

        val notificationHandler = spyk(NotificationHandler())

        assertThat(outContent.toString()).isEqualTo("")
        assertThat(Assertions.catchThrowable { notificationHandler.onNext(this.notifiable) }).isInstanceOf(NullPointerException::class.java)
    }


}
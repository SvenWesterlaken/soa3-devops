package soa.svenwstrl.notification

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.management.Sprint
import soa.svenwstrl.notifications.Notifiable
import soa.svenwstrl.notifications.NotificationHandler
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NotificationHandlerTest {

    private lateinit var notifiable: Notifiable

    private lateinit var outContent: ByteArrayOutputStream
    private lateinit var errContent: ByteArrayOutputStream
    private val originalOut = System.out
    private val originalErr = System.err

    @BeforeEach
    fun setUpStreams() {
        outContent = ByteArrayOutputStream()
        errContent = ByteArrayOutputStream()

        System.setOut(PrintStream(outContent))
        System.setErr(PrintStream(errContent))
    }


    @AfterAll
    fun restoreStreams() {
        System.setOut(originalOut)
        System.setErr(originalErr)
    }

    @BeforeEach
    fun refreshNotifiable() {
        this.notifiable = spyk(Sprint(Sprint.SprintType.REVIEW, "TestSprint", Date(), Date(), mockk(relaxed = true)))
    }

    @Test
    fun `Email Notification`() {
        every { notifiable.getSelectedTypes() } returns arrayListOf(Notifiable.Type.EMAIL)

        val notificationHandler = spyk(NotificationHandler())

        notificationHandler.onNext(notifiable)

        assertThat(outContent.toString()).isEqualTo("TO: test@email.com | This is an email message")
    }

    @Test
    fun `Slack Notification`() {
        every { notifiable.getSelectedTypes() } returns arrayListOf(Notifiable.Type.SLACK)

        val notificationHandler = spyk(NotificationHandler())

        notificationHandler.onNext(notifiable)

        assertThat(outContent.toString()).isEqualTo("TOKEN: 8a0d7fd810 | This is an slack message")
    }

}
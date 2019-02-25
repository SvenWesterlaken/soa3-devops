package soa.svenwstrl

import io.mockk.mockk
import io.mockk.spyk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.management.Sprint
import soa.svenwstrl.report.PDFReportGenerator
import soa.svenwstrl.report.PNGReportGenerator
import java.io.ByteArrayOutputStream
import java.util.*
import java.io.PrintStream


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReportGeneratorTest {
    private val outContent = ByteArrayOutputStream()
    private val errContent = ByteArrayOutputStream()
    private val originalOut = System.out
    private val originalErr = System.err

    private lateinit var pipeline: Pipeline
    private lateinit var sprint: Sprint

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
    fun refreshSprint() {
        this.outContent.reset()
        this.pipeline = mockk(relaxed = true)
        this.sprint = Sprint(Sprint.SprintType.RELEASE, "TestSprint", Date(), Date(), pipeline)
    }

    @Test
    fun GeneratePNG() {
        val abstractGenerator = spyk(PNGReportGenerator(sprint))

        //templatetmethod
        abstractGenerator.exportReport("company", "project", 1)

        assertThat(outContent.toString()).startsWith("--- PNG Format File ---")

    }

    @Test
    fun GeneratePDF() {
        val abstractGenerator = spyk(PDFReportGenerator(sprint))

        //templatetmethod
        abstractGenerator.exportReport("company", "project", 1)

        assertThat(outContent.toString()).startsWith("--- PDF Format File ---")

    }
}
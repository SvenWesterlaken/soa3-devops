package soa.svenwstrl

import io.mockk.confirmVerified
import io.mockk.spyk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.management.Sprint
import soa.svenwstrl.report.PNGReportGenerator
import soa.svenwstrl.report.ReportGenerator
import java.util.*
import java.io.PrintStream
import java.time.LocalDateTime


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReportGeneratorTest {

    @Test
    fun GeneratePNG() {

        val pipeline = spyk(Pipeline())
        val sprint = Sprint(Sprint.SprintType.RELEASE, "TestSprint", Date(), Date(), pipeline)
        val abstractGenerator = spyk(PNGReportGenerator(sprint))

        //templatetmethod
        abstractGenerator.exportReport("company", "project", 1)

    }
}
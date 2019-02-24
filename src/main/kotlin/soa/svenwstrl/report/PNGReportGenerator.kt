package soa.svenwstrl.report;
import soa.svenwstrl.management.Sprint
import java.time.LocalDateTime

class PNGReportGenerator(sprint: Sprint): ReportGenerator(sprint){

    //basic implementation
    override fun print(
        sprintData: String,
        date: LocalDateTime,
        companyName: String,
        projectName: String,
        version: Number
    ) {
        println("--- PNG Format File ---")
        println("--- Report $projectName from company $companyName")
        //for example a burndownchart
        println("$sprintData")

        println("--- date: $date --- version: $version")

    }
}

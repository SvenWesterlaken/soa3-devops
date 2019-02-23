package soa.svenwstrl.report;

import soa.svenwstrl.management.Sprint
import java.time.LocalDateTime
import java.util.*

abstract class ReportGenerator(val sprint: Sprint) {
    private lateinit var sprintData: String
    private lateinit var date: LocalDateTime

    abstract fun print(sprintData: String, date: LocalDateTime, companyName: String, projectName: String, version: Number)

    //template method
    fun exportReport(companyName: String, projectName: String, version: Number){
        retrieveData()
        print(sprintData, date, companyName, projectName, version)
    }

    private fun retrieveData() {
       sprintData = this.sprint.getDataFromSprint()
        date = LocalDateTime.now()
    }

}

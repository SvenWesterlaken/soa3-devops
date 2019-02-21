package soa.svenwstrl.devops

import java.util.concurrent.SubmissionPublisher

class Pipeline : SubmissionPublisher<Boolean>() {

    fun execute() {
        this.submit(true)
    }


}
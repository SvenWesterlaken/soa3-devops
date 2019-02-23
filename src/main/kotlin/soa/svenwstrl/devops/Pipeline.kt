package soa.svenwstrl.devops

import java.util.concurrent.SubmissionPublisher

class Pipeline : SubmissionPublisher<Boolean>(), PipelineExecution {

    override fun execute() {
        this.submit(true)
    }

}
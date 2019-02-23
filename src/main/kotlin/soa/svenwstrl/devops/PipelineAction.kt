package soa.svenwstrl.devops

import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.devops.PipelineExecution


abstract class PipelineAction: PipelineExecution {

    // Template Method
    override fun execute() {
        readConfig()
        action()
        print()
        finish()
    }

    abstract fun action(): Any

    open fun readConfig() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    open fun print() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //next action? Hoe gaan we dit oplossen?
     open fun finish(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
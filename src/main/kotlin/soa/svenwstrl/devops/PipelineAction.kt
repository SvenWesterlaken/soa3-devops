package soa.svenwstrl.devops


abstract class PipelineAction {

    // Template Method
    fun execute() {
        readConfig()
        action()
        publishInformation()
        finish()
    }

    abstract fun action(): Any

    open fun readConfig() {
        TODO("not implemented")
    }

    open fun publishInformation() {
        TODO("not implemented")
    }

    open fun finish() {
        TODO("not implemented")
    }


}
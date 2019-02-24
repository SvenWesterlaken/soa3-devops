package soa.svenwstrl.devops

class Pipeline {
    private var succeeded: Boolean = false
    private var running: Boolean = false
    private val actions: ArrayList<PipelineAction> = ArrayList()
    private var error: Exception? = null

    // Return true if pipeline succeeded or false if failed
    fun execute(): Boolean {
        running = true

        for (action in actions) {

            try {
                action.execute()
            } catch (e: Exception) {
                this.error = e
                running = false
                return false
            }

        }

        running = false
        succeeded = true
        return true
    }

    fun isRunning(): Boolean {
        return this.running
    }

    fun succeeded(): Boolean {
        return this.succeeded && !this.running
    }

    fun getError(): Exception? {
        return this.error
    }

    fun addAction(a: PipelineAction) {
        this.actions.add(a)
    }

    fun removeAction(a: PipelineAction) {
        this.actions.remove(a)
    }

    fun getAction(i: Int): PipelineAction {
        return this.actions.get(i)
    }

    fun getActions(): ArrayList<PipelineAction> {
        return this.actions
    }



}
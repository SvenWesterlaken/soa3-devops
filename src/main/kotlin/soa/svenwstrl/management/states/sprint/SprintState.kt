package soa.svenwstrl.management.states.sprint

interface SprintState {

    fun execute()
    fun finish()
    fun startPipeline()
    fun cancel()
    fun review()
    fun release()
    fun close()
    fun getState(): SprintStateEnum

}
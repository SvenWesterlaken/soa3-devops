package soa.svenwstrl.management

import soa.svenwstrl.management.states.sprint.CreatedState
import soa.svenwstrl.management.states.sprint.SprintState
import java.util.*

class Sprint(val type: SprintType, var name: String, startDate: Date, endDate: Date) {

    val backlog: SprintBacklog = SprintBacklog()
    var state: SprintState = CreatedState(this)

    fun getStateType(): SprintState.Type {
        return this.state.getState()
    }

    fun execute() {
        this.state.execute()
    }

    fun finish() {
        this.state.finish()
    }

    fun startPipeline() {
        this.state.startPipeline()
    }

    fun cancel() {
        this.state.cancel()
    }

    fun review() {
        this.state.review()
    }

    fun release() {
        this.state.release()
    }

    fun close() {
        this.state.close()
    }

    enum class SprintType {
        RELEASE, REVIEW
    }

}
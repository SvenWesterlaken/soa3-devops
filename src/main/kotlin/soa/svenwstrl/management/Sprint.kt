package soa.svenwstrl.management

import soa.svenwstrl.management.states.sprint.CreatedState
import soa.svenwstrl.management.states.sprint.SprintState

class Sprint(val type: SprintType) {

    val currentState: SprintState = CreatedState(this)

    fun getStateType(): SprintState.Type {
        return this.currentState.getState()
    }

    enum class SprintType {
        RELEASE, REVIEW
    }

}
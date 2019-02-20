package soa.svenwstrl.management.states.sprint

import soa.svenwstrl.management.Sprint

class ExecutedState(sprint: Sprint): SprintState(sprint) {

    override fun getState(): Type {
        return SprintState.Type.CANCELED
    }

    override fun finish() {
        sprint.setState(FinishedState(sprint))
    }

}
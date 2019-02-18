package soa.svenwstrl.management.states.sprint

import soa.svenwstrl.management.Sprint

class ReleasedState(sprint: Sprint): SprintState(sprint) {

    override fun getState(): Type {
        return SprintState.Type.CANCELED
    }

}
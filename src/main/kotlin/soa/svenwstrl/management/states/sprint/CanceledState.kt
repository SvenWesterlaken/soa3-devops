package soa.svenwstrl.management.states.sprint

import soa.svenwstrl.management.Sprint

class CanceledState(sprint: Sprint): SprintState(sprint) {

    override fun getState(): Type {
        return SprintState.Type.CANCELED
    }

}
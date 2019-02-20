package soa.svenwstrl.management.states.sprint

import soa.svenwstrl.management.Sprint

class ReviewState(sprint: Sprint): SprintState(sprint) {

    override fun getState(): Type {
        return SprintState.Type.REVIEW
    }

    override fun close() {
        sprint.setState(ClosedState(sprint))
    }

}
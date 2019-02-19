package soa.svenwstrl.management.states.sprint

import soa.svenwstrl.management.Sprint

class FinishedState(sprint: Sprint): SprintState(sprint) {

    override fun getState(): Type {
        return SprintState.Type.FINISHED
    }

    override fun review() {
        sprint.state = ReviewState(sprint)
    }

    override fun startPipeline() {
        sprint.state = PipelineState(sprint)
    }

}
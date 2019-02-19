package soa.svenwstrl.management.states.sprint

import soa.svenwstrl.management.Sprint

class ReleasedState(sprint: Sprint): SprintState(sprint) {

    override fun getState(): Type {
        return SprintState.Type.CANCELED
    }

    override fun cancel() {
        sprint.state = CanceledState(sprint)
    }

    override fun startPipeline() {
        sprint.state = PipelineState(sprint)
    }

    override fun close() {
        sprint.state = ClosedState(sprint)
    }

}
package soa.svenwstrl.management.states.sprint

import soa.svenwstrl.management.Sprint

class ReleasedState(sprint: Sprint): SprintState(sprint) {

    override fun getState(): Type {
        return SprintState.Type.RELEASED
    }

    override fun cancel() {
        sprint.setState(CanceledState(sprint))
    }

    override fun startPipeline() {
        sprint.setState(PipelineState(sprint))
    }

    override fun close() {
        sprint.setState(ClosedState(sprint))
    }

}
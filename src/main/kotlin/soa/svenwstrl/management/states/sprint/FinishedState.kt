package soa.svenwstrl.management.states.sprint

import soa.svenwstrl.management.Sprint

class FinishedState(sprint: Sprint): SprintState(sprint) {

    override fun getState(): Type {
        return SprintState.Type.FINISHED
    }

    override fun review() {
        if (sprint.type == Sprint.SprintType.REVIEW) {
            sprint.setState(ReviewState(sprint))
        } else {
            TODO("NOT IMPLEMENTED")
        }

    }

    override fun startPipeline() {
        if (sprint.type == Sprint.SprintType.RELEASE) {
            sprint.setState(PipelineState(sprint))
        } else {
            TODO("NOT IMPLEMENTED")
        }

    }

    override fun cancel() {
        if (sprint.type == Sprint.SprintType.RELEASE) {
            sprint.setState(CanceledState(sprint))
        } else {
            TODO("NOT IMPLEMENTED")
        }
    }

}
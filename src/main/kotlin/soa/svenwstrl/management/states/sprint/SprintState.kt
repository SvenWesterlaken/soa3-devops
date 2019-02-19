package soa.svenwstrl.management.states.sprint

import soa.svenwstrl.management.Sprint

abstract class SprintState(val sprint: Sprint) {

    open fun execute() {
        TODO("not implemented")
    }

    open fun finish() {
        TODO("not implemented")
    }

    open fun startPipeline() {
        TODO("not implemented")
    }

    open fun cancel() {
        TODO("not implemented")
    }

    open fun review() {
        TODO("not implemented")
    }

    open fun release() {
        TODO("not implemented")
    }

    open fun close() {
        TODO("not implemented")
    }

    abstract fun getState(): Type

    enum class Type {
        CREATED, EXECUTED, FINISHED, PIPELINE, RELEASED, CANCELED, CLOSED, REVIEW
    }

}
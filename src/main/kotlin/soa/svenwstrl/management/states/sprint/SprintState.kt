package soa.svenwstrl.management.states.sprint

import soa.svenwstrl.management.Sprint

abstract class SprintState(val sprint: Sprint) {

    fun execute() {
        TODO("not implemented")
    }

    fun finish() {
        TODO("not implemented")
    }

    fun startPipeline() {
        TODO("not implemented")
    }

    fun cancel() {
        TODO("not implemented")
    }

    fun review() {
        TODO("not implemented")
    }

    fun release() {
        TODO("not implemented")
    }
    fun close() {
        TODO("not implemented")
    }

    abstract fun getState(): Type

    enum class Type {
        CREATED, EXECUTED, FINISHED, PIPELINE, RELEASED, CANCELED, CLOSED, REVIEW
    }

}
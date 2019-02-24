package soa.svenwstrl.management.states.backlogitem

import soa.svenwstrl.management.BacklogItem

abstract class BacklogItemState(protected val backlogItem: BacklogItem){

    open fun setReview() {
        TODO("not implemented")
    }

    open fun setDone() {
        TODO("not implemented")
    }

    open fun setToDo() {
        TODO("not implemented")
    }

    open fun setDoing(){

        TODO("not implemented")
    }


    abstract fun getState(): Type

    enum class Type {
        REVIEW, DONE, TODO, DOING
    }
}
package soa.svenwstrl.management.states.backlogitem

import soa.svenwstrl.management.BacklogItem

class ReviewState(backlogItem: BacklogItem): BacklogItemState(backlogItem) {
    override fun getState(): Type {
        return BacklogItemState.Type.REVIEW
    }

    override fun setDone() {
        backlogItem.setState(DoneState(backlogItem))
    }

    override fun setToDo() {
        backlogItem.setState(ToDoState(backlogItem))
    }

}
package soa.svenwstrl.management.states.backlogitem

import soa.svenwstrl.management.BacklogItem

class ToDoState(backlogItem: BacklogItem): BacklogItemState(backlogItem) {
    override fun getState(): Type {
        return BacklogItemState.Type.TODO
    }

    override fun setDoing() {
        backlogItem.setState(DoingState(backlogItem))
    }

}
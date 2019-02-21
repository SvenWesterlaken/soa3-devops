package soa.svenwstrl.management.states.backlogitem

import soa.svenwstrl.management.BacklogItem

class DoingState(backlogItem: BacklogItem): BacklogItemState(backlogItem) {
    override fun getState(): Type {
        return BacklogItemState.Type.DOING
    }

    override fun setReview() {
        backlogItem.setState(ReviewState(backlogItem))
    }



}


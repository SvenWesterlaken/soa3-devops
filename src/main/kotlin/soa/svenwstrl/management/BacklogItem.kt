package soa.svenwstrl.management


import soa.svenwstrl.management.states.backlogitem.BacklogItemState
import soa.svenwstrl.management.states.backlogitem.ToDoState
import soa.svenwstrl.notifications.Notifiable

class BacklogItem(val type: BacklogItemType): Notifiable() {

    val backlogItemActivities: ArrayList<BacklogItemActivity> = ArrayList()
    private var state: BacklogItemState = ToDoState(this)

    fun addActivity(bia: BacklogItemActivity) {
        backlogItemActivities.add(bia)
    }

    fun removeActivity(bia: BacklogItemActivity) {
        backlogItemActivities.remove(bia)
    }

    fun getStateType(): BacklogItemState.Type {
        return this.state.getState()
    }

    fun setDoing() {
        this.state.setDoing()

    }

    fun review() {
        this.state.setReview()
    }

    fun setDone() {
        this.state.setDone()
    }

    fun setState(state: BacklogItemState) {
        this.state = state
        this.submit(this)
    }


    enum class BacklogItemType {
        DOING, REVIEW, DONE, TODO
    }
}




package soa.svenwstrl.management

import soa.svenwstrl.management.states.backlogitem.BacklogItemState
import soa.svenwstrl.management.states.backlogitem.ToDoState
import soa.svenwstrl.notifications.Notifiable

class BacklogItem(val type: BacklogItemType): Notifiable(), Flow.Subscriber<Boolean> {
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
/*


    override fun onComplete() {
        setDone()
    }
*/

/*    override fun onSubscribe(subscription: Flow.Subscription) {
        this.pipelineSubscription = subscription
        subscription.request(1)
    }

    override fun onNext(success: Boolean) {
        if (success) {
            this.release()
        } else {
            this.pipelineSubscription.request(1)
        }
    }

    override fun onError(throwable: Throwable?) {
        throwable!!.printStackTrace()
    }*/

    enum class BacklogItemType {
        DOING, REVIEW, DONE, TODO
    }
}




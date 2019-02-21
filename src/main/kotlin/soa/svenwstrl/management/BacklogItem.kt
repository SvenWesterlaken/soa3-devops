package soa.svenwstrl.management

import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.management.states.backlogitem.BacklogItemState
import soa.svenwstrl.management.states.backlogitem.ToDoState
import soa.svenwstrl.notifications.Notifiable
import soa.svenwstrl.users.TeamMember
import java.util.concurrent.Flow

class BacklogItem(val type: BacklogItemType,  val pipeline: Pipeline): Notifiable(), Flow.Subscriber<Boolean> {

    private lateinit var subscription: Flow.Subscription


    override fun getReceivers(): ArrayList<TeamMember> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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
    }

    override fun onComplete() {
        setDone()
    }

     override fun onSubscribe(subscription: Flow.Subscription) {
       this.subscription = subscription
        subscription.request(1)
    }

    override fun onNext(success: Boolean) {
        if (success) {
            this.review()
        } else {
            this.subscription.request(1)
        }
    }

    override fun onError(throwable: Throwable?) {
        throwable!!.printStackTrace()
    }

    enum class BacklogItemType {
        DOING, REVIEW, DONE, TODO
    }
}




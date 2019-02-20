package soa.svenwstrl.notifications

import java.util.concurrent.Flow
import java.util.concurrent.Flow.Subscriber

abstract class Notifier: Subscriber<Notification>{
    protected lateinit var subscription: Flow.Subscription
    protected lateinit var notification: Notification

    override fun onComplete() {
        System.out.println("Done")
    }

    override fun onNext(n: Notification) {
        this.notification = n
//        n.getReceivers().forEach { r -> composeMessage(r) }
    }

    override fun onSubscribe(subscription: Flow.Subscription) {
        this.subscription = subscription
        subscription.request(1)
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }

//    // Template Method
//    fun composeMessage(teamMember: TeamMember) {
//        getAddress()
//    }
//
//    abstract fun sendMessage()
}
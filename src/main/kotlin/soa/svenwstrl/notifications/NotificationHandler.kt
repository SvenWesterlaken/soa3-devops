package soa.svenwstrl.notifications

import java.util.concurrent.Flow
import java.util.concurrent.Flow.Subscriber

class NotificationHandler: Subscriber<Notifiable> {
    protected lateinit var subscription: Flow.Subscription

    override fun onComplete() {
        System.out.println("Done")
    }

    override fun onNext(n: Notifiable) {
        //Factory method
    }

    override fun onSubscribe(subscription: Flow.Subscription) {
        this.subscription = subscription
        subscription.request(1)
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }

}
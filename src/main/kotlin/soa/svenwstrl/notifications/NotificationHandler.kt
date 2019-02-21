package soa.svenwstrl.notifications

import soa.svenwstrl.notifications.Notifiable.Type.EMAIL
import soa.svenwstrl.notifications.Notifiable.Type.SLACK
import java.util.concurrent.Flow
import java.util.concurrent.Flow.Subscriber

class NotificationHandler: Subscriber<Notifiable> {
    protected lateinit var subscription: Flow.Subscription

    override fun onComplete() {
        System.out.println("Done")
    }

    override fun onNext(n: Notifiable) {

        n.getSelectedTypes().forEach { t ->

            if (t == EMAIL) {
                SlackNotifier(n)
            } else if (t == SLACK) {
                SlackNotifier(n)
            }

        }

    }

    override fun onSubscribe(subscription: Flow.Subscription) {
        this.subscription = subscription
        subscription.request(1)
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }

}
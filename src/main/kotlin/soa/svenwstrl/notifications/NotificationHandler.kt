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

    // Factory method
    override fun onNext(n: Notifiable) {

        n.getSelectedTypes().forEach { t ->

            var notifier: Notifier? = null

            if (t == EMAIL) {
                notifier = EmailNotifier(n)
            } else if (t == SLACK) {
                notifier = SlackNotifier(n)
            }

            notifier!!.handleMessage()
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
package soa.svenwstrl.notifications

import soa.svenwstrl.users.TeamMember
import java.util.concurrent.Flow
import java.util.concurrent.SubmissionPublisher

class NotificationProcessor: SubmissionPublisher<Notification>(), Flow.Processor<Notifiable, Notification> {
    private lateinit var subscription: Flow.Subscription

    override fun onComplete() {
        close()
    }

    override fun onSubscribe(subscription: Flow.Subscription) {
        this.subscription = subscription
    }

    override fun onNext(item: Notifiable) {
        // Filter out the receivers that haven't set notification preferences (Wan't to receive no notifications)
        val receivers: Sequence<TeamMember> = item.getReceivers().asSequence().filter { m -> !m.getSelectedNotificationTypes().isEmpty() }

        submit(Notification(receivers))
        subscription.request(1)
    }

    override fun onError(throwable: Throwable?) {
        TODO("not implemented")
    }
}
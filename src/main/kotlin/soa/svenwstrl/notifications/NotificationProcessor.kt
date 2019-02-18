package soa.svenwstrl.notifications

import java.util.concurrent.Flow

class NotificationProcessor: Flow.Processor<Any, String> {
    override fun onComplete() {
        TODO("not implemented")
    }

    override fun onSubscribe(subscription: Flow.Subscription?) {
        TODO("not implemented")
    }

    override fun onNext(item: Any?) {
        TODO("not implemented")
    }

    override fun onError(throwable: Throwable?) {
        TODO("not implemented")
    }

    override fun subscribe(subscriber: Flow.Subscriber<in String>?) {
        TODO("not implemented")
    }
}
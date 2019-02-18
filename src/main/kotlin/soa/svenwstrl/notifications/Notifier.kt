package soa.svenwstrl.notifications

import java.util.concurrent.Flow
import java.util.concurrent.Flow.Subscriber

abstract class Notifier: Subscriber<Any>{

    override fun onComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSubscribe(subscription: Flow.Subscription?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNext(item: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(throwable: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
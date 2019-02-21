package soa.svenwstrl.notifications

import java.util.concurrent.Flow
import java.util.concurrent.Flow.Subscriber

abstract class Notifier: Subscriber<Notifiable>{
    protected lateinit var subscription: Flow.Subscription
    protected lateinit var notifiable: Notifiable

    override fun onComplete() {
        System.out.println("Done")
    }

    override fun onNext(n: Notifiable) {
        this.notifiable = n
        this.createMessage()
    }

    override fun onSubscribe(subscription: Flow.Subscription) {
        this.subscription = subscription
        subscription.request(1)
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    // Template Method
    fun createMessage() {
        val addressInfo = getAddressInfo()
        val subject = getSubject()
        val message = composeMessage()

        sendMessage(addressInfo, subject, message)
    }

    abstract fun getAddressInfo(): Any
    open fun getSubject(): String? {
        return null
    }
    abstract fun composeMessage(): String
    abstract fun sendMessage(addressInfo: Any, subject: String?, message: String)
}
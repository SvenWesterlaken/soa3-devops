package soa.svenwstrl.notifications

abstract class Notifier(protected val notifiable: Notifiable) {
    protected lateinit var addressInfo: Any
    protected var subject: String? = null
    protected lateinit var message: String

    // Template Method
    fun handleMessage() {
        addressInfo = gatherAddressInfo()
        subject = gatherSubject()
        message = composeMessage()

        sendMessage()
    }

    abstract fun gatherAddressInfo(): Any
    open fun gatherSubject(): String? {
        return this.subject
    }
    abstract fun composeMessage(): String
    abstract fun sendMessage()
}
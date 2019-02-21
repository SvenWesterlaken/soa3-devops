package soa.svenwstrl.notifications

abstract class Notifier(protected val notifiable: Notifiable) {

    init {
        handleMessage()
    }

    // Template Method
    fun handleMessage() {
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
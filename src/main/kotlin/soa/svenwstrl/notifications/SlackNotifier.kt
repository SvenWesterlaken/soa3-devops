package soa.svenwstrl.notifications

class SlackNotifier(notifiable: Notifiable): Notifier(notifiable) {

    override fun getAddressInfo(): Any {
        TODO("not implemented")
    }

    override fun composeMessage(): String {
        TODO("not implemented")
    }

    override fun sendMessage(addressInfo: Any, subject: String?, message: String) {
        TODO("not implemented")
    }

}
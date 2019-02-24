package soa.svenwstrl.notifications

class EmailNotifier(notifiable: Notifiable): Notifier(notifiable) {

    override fun gatherAddressInfo(): Any {
        TODO("not implemented")
    }

    override fun composeMessage(): String {
        TODO("not implemented")
    }

    override fun sendMessage() {
        TODO("not implemented")
    }

}
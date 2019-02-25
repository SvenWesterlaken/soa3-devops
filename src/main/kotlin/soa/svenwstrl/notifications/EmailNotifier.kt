package soa.svenwstrl.notifications

class EmailNotifier(notifiable: Notifiable): Notifier(notifiable) {

    override fun gatherAddressInfo(): Any {
        return "test@email.com"
    }

    override fun composeMessage(): String {
        return "This is an email message"
    }

    override fun sendMessage() {
        System.out.print("TO: $addressInfo | $message")
    }

}
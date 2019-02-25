package soa.svenwstrl.notifications

class SlackNotifier(notifiable: Notifiable): Notifier(notifiable) {

    override fun gatherAddressInfo(): Any {
        return "8a0d7fd810"
    }

    override fun composeMessage(): String {
        return "This is an slack message"
    }

    override fun sendMessage() {
        System.out.print("TOKEN: $addressInfo | $message")
    }

}
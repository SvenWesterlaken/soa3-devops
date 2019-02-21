package soa.svenwstrl.users

abstract class TeamMember(name: String, private var notificationTypes: ArrayList<Notification.Type> = ArrayList()) {

    private var name = name
        get() = field
        set(value) {
            field = value
        }

    fun getSelectedNotificationTypes(): ArrayList<Notification.Type> {
        return notificationTypes
    }



    fun addNotificationType(t: Notification.Type): Boolean {
        return notificationTypes.add(t)
    }

    fun removeNotificationType(t: Notification.Type): Boolean {
        return notificationTypes.remove(t)
    }



}
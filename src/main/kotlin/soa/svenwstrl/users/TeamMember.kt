package soa.svenwstrl.users

import soa.svenwstrl.notifications.Notifiable

abstract class TeamMember(name: String, private var notificationTypes: ArrayList<Notifiable.Type> = ArrayList()) {

    private var name = name
        get() = field
        set(value) {
            field = value
        }

    fun getSelectedNotificationTypes(): ArrayList<Notifiable.Type> {
        return notificationTypes
    }

    fun addNotificationType(t: Notifiable.Type): Boolean {
        return notificationTypes.add(t)
    }

    fun removeNotificationType(t: Notifiable.Type): Boolean {
        return notificationTypes.remove(t)
    }



}
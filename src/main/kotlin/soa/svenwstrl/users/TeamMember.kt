package soa.svenwstrl.users

import soa.svenwstrl.notifications.Notifiable

abstract class TeamMember(private var name: String, private val notificationTypes: ArrayList<Notifiable.Type> = ArrayList()) {

    fun setName(n: String) {
        this.name = n
    }

    fun getName(): String {
        return this.name
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

    abstract fun getRole(): Role

    enum class Role {
        DEVELOPER, PRODUCT_OWNER, SCRUM_MASTER
    }



}
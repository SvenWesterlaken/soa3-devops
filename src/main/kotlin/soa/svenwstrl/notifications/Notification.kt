package soa.svenwstrl.notifications

import soa.svenwstrl.users.TeamMember

class Notification(private val receivers: Sequence<TeamMember>, private val type: Type) {

    fun getType(): Type {
        return this.type
    }

    fun getReceivers(): Sequence<TeamMember> {
        return this.receivers
    }

    fun getAllNotificationTypes(): ArrayList<Type> {
        return Type.values().toCollection(ArrayList())
    }

    enum class Type {
        SLACK, EMAIL
    }

}
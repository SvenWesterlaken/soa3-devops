package soa.svenwstrl.notifications

import java.util.concurrent.SubmissionPublisher

abstract class Notifiable(): SubmissionPublisher<Notifiable>() {

    private val types: ArrayList<Type> = ArrayList()

    fun getSelectedTypes(): ArrayList<Type> {
        return this.types
    }

    fun getSelectedType(i: Int): Type {
        return this.types.get(i)
    }

    fun addType(t: Type) {
        this.types.add(t)
    }

    fun removeType(t: Type) {
        this.types.remove(t)
    }

    fun getAllNotificationTypes(): ArrayList<Notifiable.Type> {
        return Notifiable.Type.values().toCollection(ArrayList())
    }

    enum class Type {
        SLACK, EMAIL, FAX
    }

}
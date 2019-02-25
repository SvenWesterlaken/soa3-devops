package soa.svenwstrl.forum

import soa.svenwstrl.notifications.Notifiable
import soa.svenwstrl.users.TeamMember

abstract class ThreadComponent(private val creator: TeamMember, private val message: String): Notifiable() {
    protected val reactions: ArrayList<ThreadComponent> = ArrayList()

    open fun traverse() {
        this.show()
        this.reactions.forEach { c -> c.traverse() }
    }

    open fun add(c: ThreadComponent) {
        if (this.canBeEdited()) {
            this.submit(this)
            this.reactions.add(c)
        } else {
            TODO("Not Implemented")
        }
    }

    fun getCreator(): TeamMember {
        return this.creator
    }

    fun getMessage(): String {
        return this.message
    }

    abstract fun show()
    abstract fun canBeEdited(): Boolean

}
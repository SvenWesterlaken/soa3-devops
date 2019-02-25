package soa.svenwstrl.forum

import soa.svenwstrl.users.TeamMember

class ThreadReaction(private val creator: TeamMember, private val message: String): ThreadComponent {
    private val reactions: ArrayList<ThreadComponent> = ArrayList()

    override fun getCreator(): TeamMember {
        return this.creator
    }

    override fun getMessage(): String {
        return this.message
    }

    override fun traverse() {
        this.show()
        this.reactions.forEach { c -> c.traverse() }
    }

    override fun add(c: ThreadComponent) {
        this.reactions.add(c)
    }

    override fun show() {
        print("${getCreator().getName()}: ${getMessage()}\n")
    }

}
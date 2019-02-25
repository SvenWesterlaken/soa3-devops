package soa.svenwstrl.forum

import soa.svenwstrl.users.TeamMember

class ThreadReaction(creator: TeamMember, message: String, private val parent: ThreadComponent): ThreadComponent(creator, message) {

    override fun canBeEdited(): Boolean {
        return this.parent.canBeEdited()
    }

    override fun show() {
        print("${getCreator().getName()}: ${getMessage()}\n")
    }

}
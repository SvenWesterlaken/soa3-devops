package soa.svenwstrl.forum

import soa.svenwstrl.management.BacklogItem
import soa.svenwstrl.management.states.backlogitem.BacklogItemState
import soa.svenwstrl.users.TeamMember

class Thread(private var name: String, private val backLogItem: BacklogItem, creator: TeamMember, message: String): ThreadComponent(creator, message) {
    fun getBackLogItem(): BacklogItem {
        return this.backLogItem
    }

    fun getName(): String {
        return this.name
    }

    fun setName(n: String) {
        this.name = n
    }

    override fun canBeEdited(): Boolean {
        return backLogItem.getStateType() != BacklogItemState.Type.DONE
    }

    override fun show() {
        print("${getName()} (${getCreator().getName()}): ${getMessage()}\n")
    }


}
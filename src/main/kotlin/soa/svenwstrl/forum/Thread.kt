package soa.svenwstrl.forum

import soa.svenwstrl.management.BacklogItem
import soa.svenwstrl.notifications.Notifiable
import soa.svenwstrl.users.TeamMember

class Thread(private var name: String, private val backLogItem: BacklogItem, private val creator: TeamMember, private val message: String): Notifiable(), ThreadComponent {
    private val reactions: ArrayList<ThreadComponent> = ArrayList()

    fun getBackLogItem(): BacklogItem {
        return this.backLogItem
    }

    fun getName(): String {
        return this.name
    }

    fun setName(n: String) {
        this.name = n
    }

    override fun getCreator(): TeamMember {
        return this.creator
    }

    override fun getMessage(): String {
        return this.message
    }

    override fun traverse() {
        this.show()
        this.reactions.forEach {c -> c.traverse() }
    }

    override fun add(c: ThreadComponent) {
        if (!backLogItem.isClosed) {
            this.submit(this)
            this.reactions.add(c)
        }
    }

    override fun show() {
        print("${getName()} (${getCreator()}): ${getMessage()}")
    }


}
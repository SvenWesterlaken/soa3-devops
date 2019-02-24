package soa.svenwstrl.forum

import soa.svenwstrl.management.BacklogItem
import soa.svenwstrl.notifications.Notifiable

class Thread(private var name: String, private val backLogItem: BacklogItem): Notifiable() {

    private val reactions: ArrayList<ThreadReaction> = ArrayList()

    fun getBackLogItem(): BacklogItem {
        return this.backLogItem
    }

    fun getName(): String {
        return this.name
    }

    fun setName(n: String) {
        this.name = n
    }


}
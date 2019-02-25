package soa.svenwstrl.management

class SprintBacklog {
    private val backlogItems: ArrayList<BacklogItem> = ArrayList()

    fun addItem(bli: BacklogItem) {
        backlogItems.add(bli)
    }

    fun removeItem(bli: BacklogItem) {
        backlogItems.remove(bli)
    }

    fun getBacklogItems(): ArrayList<BacklogItem> {
        return this.backlogItems
    }

    fun getBacklogItem(i: Int): BacklogItem {
        return this.backlogItems.get(i)
    }
}
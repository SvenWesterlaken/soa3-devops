package soa.svenwstrl.management

class SprintBacklog {
    val backlogItems: ArrayList<BacklogItem> = ArrayList()

    fun addItem(bli: BacklogItem) {
        backlogItems.add(bli)
    }

    fun removeItem(bli: BacklogItem) {
        backlogItems.remove(bli)
    }
}
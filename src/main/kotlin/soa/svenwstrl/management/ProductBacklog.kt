package soa.svenwstrl.management

class ProductBacklog {
    val backlogItems: ArrayList<BacklogItem> = ArrayList()

    fun addItem(bli: BacklogItem) {
        backlogItems.add(bli)
    }

    fun removeItem(bli: BacklogItem) {
        backlogItems.remove(bli)
    }
}
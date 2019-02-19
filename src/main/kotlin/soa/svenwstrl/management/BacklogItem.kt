package soa.svenwstrl.management

class BacklogItem {
    val backlogItemActivities: ArrayList<BacklogItemActivity> = ArrayList()

    fun addActivity(bia: BacklogItemActivity) {
        backlogItemActivities.add(bia)
    }

    fun removeActivity(bia: BacklogItemActivity) {
        backlogItemActivities.remove(bia)
    }
}

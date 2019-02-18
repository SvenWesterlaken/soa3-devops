package soa.svenwstrl.main

import soa.svenwstrl.forum.Forum
import soa.svenwstrl.management.ProductBacklog
import soa.svenwstrl.management.SprintBacklog
import soa.svenwstrl.users.ProductOwner
import soa.svenwstrl.users.TeamMember

class Project(var name: String, private val productOwner: ProductOwner) {

    val productBacklog: ProductBacklog = ProductBacklog()
    val sprintBacklogs: ArrayList<SprintBacklog> = ArrayList()
    val forum: Forum = Forum()
    val members: ArrayList<TeamMember> = ArrayList()

    fun main(args: Array<String>) {
        println("Start Project");
    }

    fun addSprintBacklog(sbl: SprintBacklog) {
        sprintBacklogs.add(sbl);
    }

    fun removeSprintBacklog(sbl: SprintBacklog) {
        sprintBacklogs.remove(sbl);
    }
}
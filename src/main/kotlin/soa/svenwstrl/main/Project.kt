package soa.svenwstrl.main

import soa.svenwstrl.forum.Forum
import soa.svenwstrl.management.ProductBacklog
import soa.svenwstrl.management.SprintBacklog
import soa.svenwstrl.users.ProductOwner
import soa.svenwstrl.users.TeamMember

class Project(private var name: String, private var productOwner: ProductOwner) {

    private val productBacklog: ProductBacklog = ProductBacklog()
    private val sprintBacklogs: ArrayList<SprintBacklog> = ArrayList()
    private val forum: Forum = Forum()
    private val members: ArrayList<TeamMember> = ArrayList()

    fun main(args: Array<String>?) {
        System.out.print("Start Project");
    }

    fun getName(): String {
        return this.name
    }

    fun setName(n: String) {
        this.name = n
    }

    fun addSprintBacklog(sbl: SprintBacklog) {
        sprintBacklogs.add(sbl);
    }

    fun removeSprintBacklog(sbl: SprintBacklog) {
        sprintBacklogs.remove(sbl);
    }

    fun getSprintBacklogs(): ArrayList<SprintBacklog> {
        return this.sprintBacklogs
    }

    fun getSprintBacklog(i: Int): SprintBacklog {
        return this.sprintBacklogs.get(i)
    }

    fun setProductOwner(po: ProductOwner) {
        this.productOwner = po
    }

    fun getProductOwner(): ProductOwner {
        return this.productOwner
    }

    fun getProductBacklog(): ProductBacklog {
        return this.productBacklog
    }

    fun getForum(): Forum {
        return this.forum
    }

    fun getMembers(): ArrayList<TeamMember> {
        return this.members
    }

    fun addMember(m: TeamMember) {
        if (m.getRole() == TeamMember.Role.PRODUCT_OWNER) {
            TODO("Not implemented")
        } else {
            this.members.add(m)
        }
    }

    fun removeMember(m: TeamMember) {
        if (m.getRole() == TeamMember.Role.PRODUCT_OWNER) {
            TODO("Not implemented")
        } else {
            this.members.remove(m)
        }
    }

    fun getMember(i: Int): TeamMember {
        return this.members.get(i)
    }
}
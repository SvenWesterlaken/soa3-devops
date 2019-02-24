package soa.svenwstrl.forum

import soa.svenwstrl.users.TeamMember

interface ThreadComponent {

    fun traverse()
    fun add(c: ThreadComponent)
    fun getCreator(): TeamMember
    fun getMessage(): String
    fun show()

}
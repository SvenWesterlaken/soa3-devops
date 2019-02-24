package soa.svenwstrl.users

class ScrumMaster (name: String) : TeamMember(name) {

    override fun getRole(): Role {
        return TeamMember.Role.SCRUM_MASTER
    }

}
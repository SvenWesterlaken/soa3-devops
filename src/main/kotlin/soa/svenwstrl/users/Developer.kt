package soa.svenwstrl.users

class Developer(name: String) : TeamMember(name)  {

    override fun getRole(): Role {
        return TeamMember.Role.DEVELOPER
    }

}
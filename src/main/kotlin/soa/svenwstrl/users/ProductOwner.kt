package soa.svenwstrl.users

class ProductOwner (name: String) : TeamMember(name) {

    override fun getRole(): Role {
        return TeamMember.Role.PRODUCT_OWNER
    }


}
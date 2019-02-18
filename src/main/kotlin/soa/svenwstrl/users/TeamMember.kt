package soa.svenwstrl.users

abstract class TeamMember(name: String) {

    var name = name
        get() = field
        set(value) {
            field = value
        }

}
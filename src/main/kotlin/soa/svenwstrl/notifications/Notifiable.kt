package soa.svenwstrl.notifications

import soa.svenwstrl.users.TeamMember
import java.util.concurrent.Flow

interface Notifiable: Flow.Publisher<Notifiable> {

    fun getMessage(): String
    fun getRecievers(): ArrayList<TeamMember>

}
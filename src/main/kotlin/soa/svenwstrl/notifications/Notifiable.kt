package soa.svenwstrl.notifications

import java.util.concurrent.SubmissionPublisher

abstract class Notifiable: SubmissionPublisher<Notifiable>() {

//    abstract fun getReceivers(): ArrayList<TeamMember>

}
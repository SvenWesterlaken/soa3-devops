package soa.svenwstrl.management

import soa.svenwstrl.devops.Pipeline
import soa.svenwstrl.management.states.sprint.CreatedState
import soa.svenwstrl.management.states.sprint.SprintState
import soa.svenwstrl.management.states.sprint.SprintState.Type.*
import soa.svenwstrl.notifications.Notifiable
import soa.svenwstrl.users.TeamMember
import java.io.File
import java.util.*
import java.util.concurrent.Flow
import kotlin.collections.ArrayList

class Sprint(private val type: SprintType, private var name: String, private var startDate: Date, private var endDate: Date, val pipeline: Pipeline): Notifiable(), Flow.Subscriber<Boolean> {

    private val backlog: SprintBacklog = SprintBacklog()
    private var state: SprintState = CreatedState(this)
    private var members: ArrayList<TeamMember> = ArrayList()
    private lateinit var pipelineSubscription: Flow.Subscription
    private var reviewSummary: File? = null

    init {
        this.pipeline.subscribe(this)
    }

    fun getName(): String {
        return this.name
    }

    fun setName(value: String) {
        if (this.getStateType() != PIPELINE && this.getStateType() != EXECUTED) {
            this.name = value
        } else {
            TODO("Not Implemented")
        }
    }

    fun getStartDate(): Date {
        return this.startDate
    }

    fun setStartDate(value: Date) {
        if (this.getStateType() != PIPELINE && this.getStateType() != EXECUTED) {
            this.startDate = value
        } else {
            TODO("Not Implemented")
        }
    }

    fun getEndDate(): Date {
        return this.endDate
    }

    fun setEndDate(value: Date) {
        if (this.getStateType() != PIPELINE && this.getStateType() != EXECUTED) {
            this.endDate = value
        } else {
            TODO("Not Implemented")
        }
    }

    fun getReviewSummary(): File? {
        return this.reviewSummary
    }

    fun getSprintType(): SprintType {
        return this.type
    }

    fun getStateType(): SprintState.Type {
        return this.state.getState()
    }

    fun getDataFromSprint(): String {
        //for example burndownchart, teammembers, teamvelocity, etc
        return "Data from Sprint";
    }


    fun execute() {
        this.state.execute()
    }

    fun finish() {
        this.state.finish()
    }

    fun startPipeline() {
        this.state.startPipeline()
        this.pipeline.execute()
    }

    fun cancel() {
        this.state.cancel()
        this.submit(this)
    }

    fun review() {
        this.state.review()
    }

    fun release() {
        this.state.release()
    }

    fun closeSprint(summary: File? = null) {
        if (this.getStateType() == REVIEW) {
            this.reviewSummary = summary
        }

        this.state.close()
    }

    fun setState(state: SprintState) {
        this.state = state
    }

    fun addTeamMember(m: TeamMember) {
        this.members.add(m)
    }

    fun removeTeamMember(m: TeamMember) {
        this.members.remove(m)
    }

    override fun onComplete() {
        close()
    }

    override fun onSubscribe(subscription: Flow.Subscription) {
        this.pipelineSubscription = subscription
        subscription.request(1)
    }

    override fun onNext(success: Boolean) {
        if (success) {
            this.release()
        } else {
            this.pipelineSubscription.request(1)
        }
    }

    override fun onError(throwable: Throwable?) {
        throwable!!.printStackTrace()
    }

    enum class SprintType {
        RELEASE, REVIEW
    }

}
package soa.svenwstrl.scm.tools

import soa.svenwstrl.main.Project
import soa.svenwstrl.scm.VersionControlConnection
import soa.svenwstrl.scm.VersionControlSystem

class Bitbucket: VersionControlConnection {
    lateinit var versionControlSystem: VersionControlSystem

    fun Bitbucket(repository: Project) {
        this.versionControlSystem = VersionControlSystem(repository)
    }

    override fun createConnection(): VersionControlSystem {
        return versionControlSystem
    }
}
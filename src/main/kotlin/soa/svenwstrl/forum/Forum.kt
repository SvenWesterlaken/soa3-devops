package soa.svenwstrl.forum

class Forum {

    private val threads: ArrayList<Thread> = ArrayList()

    fun addThread(t: Thread) {
        this.threads.add(t)
    }

    fun getThreads(): ArrayList<Thread> {
        return this.threads
    }

}
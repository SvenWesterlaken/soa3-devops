package soa.svenwstrl.forum

class Forum {

    private val threads: ArrayList<Thread> = ArrayList()

    fun addThread(t: Thread) {
        this.threads.add(t)
    }

    fun removeThread(t: Thread) {
        this.threads.remove(t)
    }

    fun getThreads(): ArrayList<Thread> {
        return this.threads
    }

    fun getThread(i: Int): Thread {
        return this.threads.get(i)
    }

}
package soa.svenwstrl.main

import soa.svenwstrl.users.ProductOwner

class Project(name: String, private val productOwner: ProductOwner) {

    fun main(args: Array<String>) {
        println("Start Project");
    }
}
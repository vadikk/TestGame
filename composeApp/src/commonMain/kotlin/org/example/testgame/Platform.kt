package org.example.testgame

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
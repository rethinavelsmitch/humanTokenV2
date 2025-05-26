package com.smitch.humantokenv2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
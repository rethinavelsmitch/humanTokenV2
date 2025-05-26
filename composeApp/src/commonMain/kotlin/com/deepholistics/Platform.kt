package com.deepholistics

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
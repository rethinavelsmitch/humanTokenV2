package com.deepholistics

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello PuviArasu, ${platform.name}!"
    }
}
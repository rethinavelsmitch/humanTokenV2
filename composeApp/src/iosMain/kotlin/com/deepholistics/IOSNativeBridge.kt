package com.deepholistics

interface IOSNativeBridge {
    fun nativeEncrypt(data: String): String
    fun nativeDecrypt(data: String): String
}
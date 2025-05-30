package com.deepholistics.data.networking

interface EncryptionManager {
    fun decrypt(data: String): String
    fun encrypt(data: String): String
}

expect fun provideCryptoManager(): EncryptionManager

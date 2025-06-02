package com.deepholistics.data.networking

import com.deepholistics.IOSNativeBridge

lateinit var isoNativeBridge: IOSNativeBridge

actual fun encrypt(data: String): String {
    return isoNativeBridge.nativeEncrypt(data)
}

actual fun decrypt(data: String): String {
    return isoNativeBridge.nativeDecrypt(data)
}


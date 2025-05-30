package com.deepholistics.data.networking

actual fun provideCryptoManager(): EncryptionManager = EncryptionManagerImpl()
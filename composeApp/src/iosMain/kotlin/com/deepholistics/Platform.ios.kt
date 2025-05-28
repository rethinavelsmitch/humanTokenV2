package com.deepholistics

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()


actual val dashboardIcon: ImageVector
    get() = ImageVector.Builder(defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 0f, viewportHeight = 0f).build()
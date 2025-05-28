package com.deepholistics

import androidx.compose.ui.graphics.vector.ImageVector

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


expect val dashboardIcon: ImageVector

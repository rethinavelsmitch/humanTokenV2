package com.deepholistics

import androidx.compose.ui.graphics.vector.ImageVector
import io.ktor.client.HttpClient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


expect val dashboardIcon: ImageVector
expect val bioMarkerIcon: ImageVector
expect val recommendationIcon: ImageVector
expect val marketPlaceIcon: ImageVector
expect val chatAssistant: ImageVector

expect fun httpClient(): HttpClient


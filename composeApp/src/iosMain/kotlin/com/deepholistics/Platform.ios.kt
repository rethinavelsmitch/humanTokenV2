package com.deepholistics

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import platform.UIKit.UIDevice
import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()


actual val dashboardIcon: ImageVector
    get() = ImageVector.Builder(
        defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 0f, viewportHeight = 0f
    ).build()


actual val bioMarkerIcon: ImageVector
    get() = ImageVector.Builder(
        defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 0f, viewportHeight = 0f
    ).build()


actual val recommendationIcon: ImageVector
    get() = ImageVector.Builder(
        defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 0f, viewportHeight = 0f
    ).build()


actual val marketPlaceIcon: ImageVector
    get() = ImageVector.Builder(
        defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 0f, viewportHeight = 0f
    ).build()

actual val chatAssistant: ImageVector
    get() = ImageVector.Builder(
        defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 0f, viewportHeight = 0f
    ).build()


actual fun httpClient(): HttpClient = HttpClient(Darwin) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }

    install(Logging) {
        level = io.ktor.client.plugins.logging.LogLevel.ALL
    }
}
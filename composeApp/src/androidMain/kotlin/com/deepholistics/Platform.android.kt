package com.deepholistics

import android.R.attr.level
import android.os.Build
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.graphics.vector.ImageVector
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual val dashboardIcon: ImageVector
    get() = Icons.Filled.Person
actual val bioMarkerIcon: ImageVector
    get() = Icons.Filled.Refresh
actual val recommendationIcon: ImageVector
    get() = Icons.Filled.Build
actual val marketPlaceIcon: ImageVector
    get() = Icons.Filled.Info
actual val chatAssistant: ImageVector
    get() = Icons.Filled.FavoriteBorder

actual fun httpClient(): HttpClient = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true

        })
    }

    install(Logging) {
        level = LogLevel.ALL
        object : Logger {
            override fun log(message: String) {
              println("HTTP Client: $message")
            }
        }
    }
}
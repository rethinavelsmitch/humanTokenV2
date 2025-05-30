package com.deepholistics.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(Logging) {
            level = LogLevel.ALL
        }
        
        install(HttpTimeout) {
            requestTimeoutMillis = 20_000 // 20 seconds
            connectTimeoutMillis = 20_000 // 20 seconds
            socketTimeoutMillis = 20_000 // 20 seconds
        }
        
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 3)
            retryOnException(maxRetries = 3, retryOnTimeout = true)
            exponentialDelay() // Uses exponential backoff
        }
        
        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }

        defaultRequest {
            header("client_id", "JmEfoQ2sP18APIiX9z0nY3vlDAKHIp8nKuyV")
            header("content-type", "application/json")
            header("user_timezone", "Asia/Calcutta")
            url("https://api.stg.dh.deepholistics.com/")
        }

        install(Auth) { // for refresh token

        }
    }
}
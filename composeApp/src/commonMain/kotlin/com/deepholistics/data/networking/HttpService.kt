package com.deepholistics.data.networking

import com.deepholistics.android.data.model.apiresult.ApiResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

suspend inline fun <reified T> HttpClient.get(
    url: String,
    accessToken: String? = null,
    parameters: Map<String, Any> = emptyMap(),
): Result<ApiResult> {
    return try {
        val response = this.get(url) {
            // Add access token to header if provided
            accessToken?.let {
                header("access_token", it)
            }

            // Add URL parameters
            parameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
        Result.success(response.body<ApiResult>())
    } catch (e: Exception) {
        Result.failure(e)
    }
}

suspend inline fun <reified T> HttpClient.post(
    url: String,
    accessToken: String? = null,
    body: Any? = null,
): Result<ApiResult> {
    return try {
        val response = this.post(url) {
            // Add access token to header if provided
            accessToken?.let {
                header("access_token", it)
            }

            // Set request body if provided
            body?.let {
                setBody(it)
            }
        }
        Result.success(response.body<ApiResult>())
    } catch (e: Exception) {
        Result.failure(e)
    }
}

suspend inline fun <reified T> HttpClient.put(
    url: String,
    accessToken: String? = null,
    body: Any? = null,
): Result<ApiResult> {
    return try {
        val response = this.put(url) {
            // Add access token to header if provided
            accessToken?.let {
                header("access_token", it)
            }

            // Set request body if provided
            body?.let {
                setBody(it)
            }
        }
        Result.success(response.body<ApiResult>())
    } catch (e: Exception) {
        Result.failure(e)
    }
}

suspend inline fun <reified T> HttpClient.delete(
    url: String,
    accessToken: String? = null,
): Result<ApiResult> {
    return try {
        val response = this.delete(url) {
            // Add access token to header if provided
            accessToken?.let {
                header("access_token", it)
            }
        }
        Result.success(response.body<ApiResult>())
    } catch (e: Exception) {
        Result.failure(e)
    }
}


package com.deepholistics.data.networking

import com.deepholistics.android.data.model.apiresult.ApiResult
import com.deepholistics.utils.EncryptionUtils
import com.deepholistics.utils.EncryptionUtils.toEncryptedRequestBody
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.JsonObject

suspend inline fun <reified T> HttpClient.get(
    url: String,
    accessToken: String,
    parameters: Map<String, Any> = emptyMap(),
): Result<T> {
    return try {
        val response = this.get(url) {
            header("access_token", accessToken)
            parameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }

        val responseBody = response.bodyAsText()
        println("API Response Body: $responseBody")

        // Handle encrypted response
        val decryptedData = EncryptionUtils.handleEncryptedResponse<T>(responseBody)
        if (decryptedData != null) {
            Result.success(decryptedData)
        } else {
            Result.failure(Exception("Failed to decrypt response"))
        }
    } catch (e: Exception) {
        println("API Error: ${e.message}")
        Result.failure(e)
    }
}

suspend inline fun HttpClient.post(
    url: String,
    accessToken: String? = null,
    body: JsonObject? = null,
): Result<ApiResult> {
    return try {
        val response = this.post(url) {
            // Add access token to header if provided
            accessToken?.let {
                header("access_token", it)
            }
            if (body != null) {
                // Set request body if provided
                setBody(body.toEncryptedRequestBody())
            }
        }
        Result.success(response.body<ApiResult>())
    } catch (e: Exception) {
        Result.failure(e)
    }
}

suspend inline fun HttpClient.put(
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

suspend inline fun HttpClient.delete(
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


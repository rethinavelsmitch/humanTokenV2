
package com.deepholistics.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.HttpMethod

class HttpService(private val httpClient: HttpClient) {
    
    suspend inline fun <reified T> get(
        url: String,
        accessToken: String? = null,
        parameters: Map<String, Any> = emptyMap()
    ): Result<T> {
        return try {
            val response = httpClient.get(url) {
                // Add access token to header if provided
                accessToken?.let {
                    header("Authorization", "Bearer $it")
                }
                
                // Add URL parameters
                parameters.forEach { (key, value) ->
                    parameter(key, value)
                }
            }
            Result.success(response.body<T>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend inline fun <reified T> post(
        url: String,
        accessToken: String? = null,
        body: Any? = null
    ): Result<T> {
        return try {
            val response = httpClient.post(url) {
                // Add access token to header if provided
                accessToken?.let {
                    header("Authorization", "Bearer $it")
                }
                
                // Set request body if provided
                body?.let {
                    setBody(it)
                }
            }
            Result.success(response.body<T>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend inline fun <reified T> put(
        url: String,
        accessToken: String? = null,
        body: Any? = null
    ): Result<T> {
        return try {
            val response = httpClient.put(url) {
                // Add access token to header if provided
                accessToken?.let {
                    header("Authorization", "Bearer $it")
                }
                
                // Set request body if provided
                body?.let {
                    setBody(it)
                }
            }
            Result.success(response.body<T>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend inline fun <reified T> delete(
        url: String,
        accessToken: String? = null
    ): Result<T> {
        return try {
            val response = httpClient.delete(url) {
                // Add access token to header if provided
                accessToken?.let {
                    header("Authorization", "Bearer $it")
                }
            }
            Result.success(response.body<T>())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

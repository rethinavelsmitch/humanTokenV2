package com.deepholistics.utils

import com.deepholistics.android.data.model.apiresult.ApiResult
import com.deepholistics.data.networking.decrypt
import kotlinx.serialization.json.Json

object EncryptionUtils {


    /**
     * Generic function to decrypt encrypted API response data
     * This handles the pattern where API returns encrypted data in response.data field
     */
    fun decryptApiResponse(encryptedData: String): String {
        return try {
            // Your existing decryption logic adapted for common use
            decryptString(encryptedData)
        } catch (e: Exception) {
            println("Decryption failed: ${e.message}")
            ""
        }
    }

    /**
     * Generic decryption function that can be used across platforms
     */
    private fun decryptString(encryptedText: String): String {
        return decrypt(encryptedText)
    }

    /**
     * Handles encrypted API responses generically
     * Takes raw API response and returns decrypted data if needed
     */
    inline fun <reified T> handleEncryptedResponse(
        responseBody: String,
    ): T? {
        return try {
            val json = Json { ignoreUnknownKeys = true }

            // Parse the encrypted response structure
            val encryptedResponse = json.decodeFromString<ApiResult>(responseBody)

            // Decrypt the data field
            val decryptedData = encryptedResponse.data?.let { decryptApiResponse(it) }

            // Parse the decrypted data as the expected type
            if (decryptedData?.isNotEmpty() == true) {
                json.decodeFromString<T>(decryptedData)
            } else {
                null
            }
        } catch (e: Exception) {
            println("Failed to handle encrypted response: ${e.message}")
            null
        }
    }
}


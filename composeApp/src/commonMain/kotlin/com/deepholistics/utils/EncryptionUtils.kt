package com.deepholistics.utils

import com.deepholistics.data.networking.decrypt
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.toByteArray
import kotlinx.serialization.json.Json

private const val algorithm = "AES"
private const val tokenKey = "4xlmO9YC71qv28qfWHJ4LVEYm6gNNuYi" //staging
private const val padding = "AES/CBC/PKCS5Padding"
private const val initVector = "lTayxxaZZfGhRs3j" //staging
private const val ivSize = 16
private const val keySize = 32

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
            val encryptedResponse = json.decodeFromString<EncryptedApiResponse>(responseBody)

            // Decrypt the data field
            val decryptedData = decryptApiResponse(encryptedResponse.data)

            // Parse the decrypted data as the expected type
            if (decryptedData.isNotEmpty()) {
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

/**
 * Data class to represent encrypted API response structure
 */
@kotlinx.serialization.Serializable
data class EncryptedApiResponse(
    val status: String,
    val message: String,
    val data: String, // This field contains the encrypted data
)

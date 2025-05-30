
package com.deepholistics.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

object EncryptionUtils {
    
    // Your encryption constants - you'll need to define these
    private const val tokenKey = "your_token_key_here" // Replace with your actual key
    private const val initVector = "your_init_vector" // Replace with your actual IV
    private const val padding = "AES/CBC/PKCS5Padding"
    private const val algorithm = "AES"
    private const val keySize = 16
    private const val ivSize = 16
    
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
        // This is a placeholder implementation
        // You'll need to implement the actual decryption logic using expect/actual
        // or a common crypto library that works across platforms
        
        // For now, returning the input as-is
        // You should implement the actual AES decryption here
        return encryptedText
    }
    
    /**
     * Handles encrypted API responses generically
     * Takes raw API response and returns decrypted data if needed
     */
    inline fun <reified T> handleEncryptedResponse(
        responseBody: String,
        isEncrypted: Boolean = true
    ): T? {
        return try {
            val json = Json { ignoreUnknownKeys = true }
            
            if (isEncrypted) {
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
            } else {
                // Direct parsing for non-encrypted responses
                json.decodeFromString<T>(responseBody)
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
    val data: String // This field contains the encrypted data
)

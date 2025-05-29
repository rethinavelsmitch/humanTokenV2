package com.deepholistics.data.repository


import com.deepholistics.android.data.model.apiresult.ApiResult
import com.deepholistics.data.api.HealthApiService
import com.deepholistics.data.networking.get
import com.deepholistics.data.networking.post
import com.deepholistics.httpClient

class CommonRepository {
    private val healthApiService = HealthApiService()
    private val httpClient = httpClient()
    
    suspend fun getHealthOverview(): Result<com.deepholistics.data.models.ApiResult> {
        return healthApiService.getHealthOverview()
    }
    
    suspend fun makeApiCall(
        endpoint: String,
        accessToken: String? = null,
        parameters: Map<String, Any> = emptyMap()
    ): Result<ApiResult> {
        return httpClient.get<ApiResult>(
            url = endpoint,
            accessToken = accessToken,
            parameters = parameters
        )
    }
    
    suspend fun postData(
        endpoint: String,
        accessToken: String? = null,
        body: Any? = null
    ): Result<ApiResult> {
        return httpClient.post<ApiResult>(
            url = endpoint,
            accessToken = accessToken,
            body = body
        )
    }
    
    suspend fun getUserProfile(accessToken: String): Result<ApiResult> {
        return makeApiCall(
            endpoint = "https://api.stg.dh.deepholistics.com/v4/human-token/profile",
            accessToken = accessToken
        )
    }
    
    suspend fun updateUserProfile(
        accessToken: String,
        profileData: Any
    ): Result<ApiResult> {
        return postData(
            endpoint = "https://api.stg.dh.deepholistics.com/v4/human-token/profile/update",
            accessToken = accessToken,
            body = profileData
        )
    }
}

package com.deepholistics.onboard.viewmodel

import com.deepholistics.android.data.model.apiresult.ApiResult
import com.deepholistics.data.models.apiresult.RecommendationData
import com.deepholistics.data.repository.CommonRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val httpClient: HttpClient) {


    private val _apiState = MutableStateFlow<ApiResult?>(null)
    val apiState: StateFlow<ApiResult?> = _apiState.asStateFlow()

    private val commonRepository = CommonRepository(httpClient)
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)


    private val accessToken =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiNWZjZGMyNDgtYTJmOC00ZTZhLTg1MzItMzlkMmJhYmM2YzgxIiwic2Vzc2lvbl9pZCI6IjQzZjNmMTExLWVhNTgtNDI4NC04YjUzLTA0MDA1Mzc0NGM1NSIsInVzZXJfaW50X2lkIjoiODkiLCJpYXQiOjE3NDg4Njc5OTQsImV4cCI6MTc0OTQ3Mjc5NH0.x9n5yJUuJx_naA3rarsOpGBcWw93NMO-CWdpr4fzCuU"
    private val _logOutState = MutableStateFlow<ApiResult?>(null)
    val logOutState: StateFlow<ApiResult?> = _logOutState.asStateFlow()

    fun userLogOut() {
        viewModelScope.launch {
            val response = commonRepository.userLogOut(accessToken = accessToken)
            response.fold(
                onSuccess = { apiResult ->
                    // _apiState.value = apiResult
                    _apiState.value = ApiResult(
                        isSuccessful = true,
                        message = apiResult.message,
                        errorMessage = null
                    )
                    println("Recommendation--> success: ${apiResult.message}")
                },
                onFailure = { exception ->
                    _apiState.value = ApiResult(
                        isSuccessful = false,
                        message = "Failed to get health overview",
                        errorMessage = exception.message
                    )
                    println("Recommendation--> Error: ${exception.message}")
                }
            )
        }
    }

}
package com.deepholistics.onboard.viewmodel

import com.deepholistics.android.data.model.apiresult.ApiResult
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


    private val accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiSU5UXzBiODkzN2IzLTU0M2ItNDYzYy1iODFjLWZmNzJkM2U1NWE2OCIsInNlc3Npb25faWQiOiI2MjA1ZDhhNy0wYzAxLTRhNGItYjVkZC02N2M4NWUxYjFkZDEiLCJ1c2VyX2ludF9pZCI6IjMyMCIsImlhdCI6MTc0ODU5NDUxNSwiZXhwIjoxNzQ5MTk5MzE1fQ.fiMnweusGWc86pJYruPK7KElfV3N90ZJIOh7REqmt1I"

    fun userLogOut() {
        viewModelScope.launch {
            val response = commonRepository.userLogOut(accessToken = accessToken)
            response.fold(
                onSuccess = { apiResult ->
                    // _apiState.value = apiResult
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
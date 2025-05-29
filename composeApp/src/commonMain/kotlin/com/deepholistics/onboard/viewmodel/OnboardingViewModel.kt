package com.deepholistics.onboard.viewmodel

import com.deepholistics.android.data.model.apiresult.ApiResult
import com.deepholistics.data.OnboardingState
import com.deepholistics.data.repository.CommonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel {
    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()
    
    private val commonRepository = CommonRepository()
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    
    private val _apiState = MutableStateFlow<ApiResult?>(null)
    val apiState: StateFlow<ApiResult?> = _apiState.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val onboardingPages = listOf(
        "Welcome to HumanToken",
        "Secure Authentication",
        "Get Started Today"
    )

    fun getCurrentPageTitle(): String {
        return onboardingPages.getOrElse(_state.value.currentPage) { "Welcome" }
    }

    fun getTotalPages(): Int {
        return onboardingPages.size
    }

    fun nextPage() {
        val currentPage = _state.value.currentPage
        if (currentPage < onboardingPages.size - 1) {
            _state.value = _state.value.copy(currentPage = currentPage + 1)
        } else {
            _state.value = _state.value.copy(isCompleted = true)
        }
    }

    fun previousPage() {
        val currentPage = _state.value.currentPage
        if (currentPage > 0) {
            _state.value = _state.value.copy(currentPage = currentPage - 1)
        }
    }

    fun skipOnboarding() {
        _state.value = _state.value.copy(isCompleted = true)
    }
    
    // API call methods using CommonRepository
    fun getHealthOverview() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = commonRepository.getHealthOverview()
                result.fold(
                    onSuccess = { apiResult ->
                        _apiState.value = apiResult
                        println("Health overview API success: ${apiResult.message}")
                    },
                    onFailure = { exception ->
                        _apiState.value = ApiResult(
                            isSuccessful = false,
                            message = "Failed to get health overview",
                            errorMessage = exception.message
                        )
                        println("Health overview API error: ${exception.message}")
                    }
                )
            } catch (e: Exception) {
                _apiState.value = ApiResult(
                    isSuccessful = false,
                    message = "Network error",
                    errorMessage = e.message
                )
                println("Health overview exception: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun getUserProfile(accessToken: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = commonRepository.getUserProfile(accessToken)
                result.fold(
                    onSuccess = { apiResult ->
                        _apiState.value = apiResult
                        println("User profile API success: ${apiResult.message}")
                    },
                    onFailure = { exception ->
                        _apiState.value = ApiResult(
                            isSuccessful = false,
                            message = "Failed to get user profile",
                            errorMessage = exception.message
                        )
                        println("User profile API error: ${exception.message}")
                    }
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun updateUserProfile(accessToken: String, profileData: Any) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = commonRepository.updateUserProfile(accessToken, profileData)
                result.fold(
                    onSuccess = { apiResult ->
                        _apiState.value = apiResult
                        println("Update profile API success: ${apiResult.message}")
                    },
                    onFailure = { exception ->
                        _apiState.value = ApiResult(
                            isSuccessful = false,
                            message = "Failed to update profile",
                            errorMessage = exception.message
                        )
                        println("Update profile API error: ${exception.message}")
                    }
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun makeCustomApiCall(
        endpoint: String,
        accessToken: String? = null,
        parameters: Map<String, Any> = emptyMap()
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = commonRepository.makeApiCall(endpoint, accessToken, parameters)
                result.fold(
                    onSuccess = { apiResult ->
                        _apiState.value = apiResult
                        println("Custom API call success: ${apiResult.message}")
                    },
                    onFailure = { exception ->
                        _apiState.value = ApiResult(
                            isSuccessful = false,
                            message = "API call failed",
                            errorMessage = exception.message
                        )
                        println("Custom API call error: ${exception.message}")
                    }
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
}

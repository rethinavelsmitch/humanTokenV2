package com.deepholistics.onboard.viewmodel

import com.deepholistics.android.data.model.apiresult.ApiResult
import com.deepholistics.data.OnboardingState
import com.deepholistics.data.models.apiresult.RecommendationResponse
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

class OnboardingViewModel(private val httpClient: HttpClient) {
    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    private val commonRepository = CommonRepository(httpClient)
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

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

    /*  // API call methods using CommonRepository
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
      }*/

    private val accessToken =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiQkVUQV8wMzcyNGE3Yi0wZjA5LTQ1ODYtYmYyMy1hYTQ1NzA5NzVhYjciLCJzZXNzaW9uX2lkIjoiOGM0MmFlMzAtZmVkMC00NTNjLWIwMzEtYmQyYmFjNzQ5N2Y0IiwidXNlcl9pbnRfaWQiOiI0NzUiLCJpYXQiOjE3NDg0OTkwODgsImV4cCI6MTc0OTEwMzg4OH0.jbbY5r1g-SSzYvII3EkcfzFfdDF2OHZwifx9DFuH20E"
    private val _recommendationState = MutableStateFlow<RecommendationResponse?>(null)
    val recommendationState: StateFlow<RecommendationResponse?> = _recommendationState.asStateFlow()

    fun getRecommendation() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = commonRepository.getRecommendation(accessToken = accessToken)
                response.fold(
                    onSuccess = { recommendationResponse ->
                        _recommendationState.value = recommendationResponse
                        _apiState.value = ApiResult(
                            isSuccessful = true,
                            message = recommendationResponse.message,
                            errorMessage = null
                        )
                        println("Recommendation success: ${recommendationResponse.message}")
                    },
                    onFailure = { exception ->
                        _apiState.value = ApiResult(
                            isSuccessful = false,
                            message = "Failed to get recommendations",
                            errorMessage = exception.message
                        )
                        println("Recommendation error: ${exception.message}")
                    }
                )
            } catch (e: Exception) {
                _apiState.value = ApiResult(
                    isSuccessful = false,
                    message = "Network error occurred",
                    errorMessage = e.message
                )
                println("Recommendation exception: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }


}

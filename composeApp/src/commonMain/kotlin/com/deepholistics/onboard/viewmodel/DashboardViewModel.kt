
package com.deepholistics.onboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepholistics.data.api.HealthApiService
import com.deepholistics.data.models.HealthOverview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.onSuccess

class DashboardViewModel : ViewModel() {

    private val apiService = HealthApiService()
    
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadHealthOverview()
    }

    fun loadHealthOverview() {

        println("--> loadHealthOverview")

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                apiService.getHealthOverview()
                    .onSuccess { healthOverview ->
    //                    _uiState.value = _uiState.value.copy(
    //                        healthOverview = healthOverview,
    //                        isLoading = false,
    //                        error = null
    //                    )
                    }
                    .onFailure { exception ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = exception.message
                        )
                    }
            } catch (e: Exception) {
               println("--> Exception: $e")
            }
        }
    }

    fun retry() {
        loadHealthOverview()
    }

    override fun onCleared() {
        super.onCleared()
        apiService.close()
    }
}

data class DashboardUiState(
    val healthOverview: HealthOverview? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

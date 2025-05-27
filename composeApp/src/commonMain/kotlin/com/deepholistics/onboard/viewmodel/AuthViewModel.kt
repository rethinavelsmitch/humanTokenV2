package com.deepholistics.onboard.viewmodel

import com.deepholistics.data.AuthState
import com.deepholistics.data.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class AuthViewModel {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private val _sampleCollectionState = MutableStateFlow(false)
    val sampleCollectionState: StateFlow<Boolean> = _sampleCollectionState.asStateFlow()

    fun sampleCollectionValidated() {
        _sampleCollectionState.value = true
    }

    suspend fun login(email: String, password: String) {
        _state.value = _state.value.copy(isLoading = true, error = null)

        try {
            // Simulate API call
            delay(2000)

            // Simple validation for demo
            if (email.isNotEmpty() && password.length >= 6) {
                val user = User(
                    id = "user_${1}", email = email, fullName = "Demo User"
                )
                _state.value = _state.value.copy(
                    isAuthenticated = true, isLoading = false, user = user, error = null
                )
            } else {
                _state.value = _state.value.copy(
                    isLoading = false, error = "Invalid email or password"
                )
            }
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                isLoading = false, error = "Login failed: ${e.message}"
            )
        }
    }


    suspend fun createAccount(email: String, password: String, fullName: String) {
        _state.value = _state.value.copy(isLoading = true, error = null)

        try {
            // Simulate API call
            delay(2000)

            if (email.isNotEmpty() && password.length >= 6 && fullName.isNotEmpty()) {
                val user = User(
                    id = "user_${1}", email = email, fullName = fullName
                )
                _state.value = _state.value.copy(
                    isAuthenticated = true, isLoading = false, user = user, error = null
                )
            } else {
                _state.value = _state.value.copy(
                    isLoading = false, error = "Please fill all fields correctly"
                )
            }
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                isLoading = false, error = "Account creation failed: ${e.message}"
            )
        }
    }

    fun logout() {
        _state.value = AuthState()
    }

    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }

    fun setAuthenticated(isAuthenticated: Boolean) {
        _state.value = _state.value.copy(
            isAuthenticated = isAuthenticated, isLoading = false, user = null, error = null
        )
        println("_state.Value ${_state.value}")
    }
}

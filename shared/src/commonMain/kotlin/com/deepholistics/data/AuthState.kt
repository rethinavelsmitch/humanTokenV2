package com.deepholistics.data

data class AuthState(
    val isAuthenticated: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = "",
    val user: User = User("", "", "")
)

data class User(
    val id: String, val email: String, val fullName: String
)

data class OnboardingState(
    val currentPage: Int = 0, val isCompleted: Boolean = false
)
package com.deepholistics.onboard.viewmodel

import com.deepholistics.data.OnboardingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//class OnboardingViewModel {
//
//    private val _state = MutableStateFlow(OnboardingState())
//    val state: StateFlow<OnboardingState> = _state.asStateFlow()
//
//    private val onboardingPages = listOf(
//        "Welcome to HumanToken",
//        "Secure Authentication",
//        "Cross-Platform Experience",
//        "Get Started"
//    )
//
//    fun nextPage() {
//        val currentState = _state.value
//        if (currentState.currentPage < onboardingPages.size - 1) {
//            _state.value = currentState.copy(
//                currentPage = currentState.currentPage + 1
//            )
//        } else {
//            completeOnboarding()
//        }
//    }
//
//    fun previousPage() {
//        val currentState = _state.value
//        if (currentState.currentPage > 0) {
//            _state.value = currentState.copy(
//                currentPage = currentState.currentPage - 1
//            )
//        }
//    }
//
//    fun skipOnboarding() {
//        completeOnboarding()
//    }
//
//    private fun completeOnboarding() {
//        _state.value = _state.value.copy(isCompleted = true)
//    }
//
//    fun getCurrentPageTitle(): String {
//        return onboardingPages.getOrNull(_state.value.currentPage) ?: ""
//    }
//
//    fun getTotalPages(): Int = onboardingPages.size
//}



class OnboardingViewModel {
    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

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
}

package com.deepholistics.onboard

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.deepholistics.onboard.viewmodel.AuthViewModel
import com.deepholistics.onboard.viewmodel.OnboardingViewModel
import io.ktor.client.HttpClient

enum class Screen {
    ONBOARDING, LOGIN, CREATE_ACCOUNT, PAYMENT, HEALTH_PROFILE, SAMPLE_COLLECTION, SCHEDULE_BLOOD_TEST, MAIN, PROFILE
}

@Composable
fun App(httpClient: HttpClient) {
    var currentScreen by remember { mutableStateOf(Screen.SCHEDULE_BLOOD_TEST) }
    val authViewModel = remember { AuthViewModel() }
    val onboardingViewModel = remember { OnboardingViewModel(httpClient) }
    MaterialTheme {
        when (currentScreen) {
            Screen.ONBOARDING -> {
                OnboardingScreen(
                    viewModel = onboardingViewModel,
                    onCompleted = { currentScreen = Screen.LOGIN })
            }

            Screen.LOGIN -> {
                LoginScreen(
                    authViewModel = authViewModel,
                    onLoginSuccess = { currentScreen = Screen.CREATE_ACCOUNT })
            }

            Screen.CREATE_ACCOUNT -> {
                CreateAccountScreen(
                    authViewModel = authViewModel,
                    onNavigateToHealthProfile = { currentScreen = Screen.HEALTH_PROFILE },
                )
            }

            Screen.HEALTH_PROFILE -> {
                HealthProfileScreen(
                    authViewModel = authViewModel,
                    onNavigateToSampleCollection = { currentScreen = Screen.SAMPLE_COLLECTION })
            }

            Screen.SAMPLE_COLLECTION -> {
                SampleCollectionScreen(
                    authViewModel = authViewModel,
                    onNavigateToScheduleBloodTest = {
                        currentScreen = Screen.SCHEDULE_BLOOD_TEST
                    },
                )
            }

            Screen.SCHEDULE_BLOOD_TEST -> {
                ScheduleBloodTestScreen(onClick = {
                   onboardingViewModel.getRecommendation()
                   // currentScreen = Screen.PAYMENT
                })
            }

            Screen.PAYMENT -> {
                PaymentScreenLauncher(onClick = {
                    currentScreen = Screen.MAIN
                })
            }

            Screen.MAIN -> {
                MainScreen(onNavigateToProfile = {
                    currentScreen = Screen.PROFILE
                })
            }

            Screen.PROFILE -> {
                ProfileScreen(onNavigateBack = {
                    currentScreen = Screen.MAIN
                })
            }
        }
    }
}

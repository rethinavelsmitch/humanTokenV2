package com.deepholistics.onboard

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.deepholistics.onboard.viewmodel.AuthViewModel
import com.deepholistics.onboard.viewmodel.OnboardingViewModel


enum class Screen {
    ONBOARDING, LOGIN, CREATE_ACCOUNT, MAIN, PAYMENT
}

@Composable
fun App() {

    var currentScreen by remember { mutableStateOf(Screen.PAYMENT) }
    val authViewModel = remember { AuthViewModel() }
    val onboardingViewModel = remember { OnboardingViewModel() }

    MaterialTheme {
        when (currentScreen) {
            Screen.ONBOARDING -> {
                OnboardingScreen(
                    viewModel = onboardingViewModel, onCompleted = { currentScreen = Screen.LOGIN })
            }

            Screen.LOGIN -> {
                LoginScreen(
                    authViewModel = authViewModel,
                    onNavigateToCreateAccount = { currentScreen = Screen.CREATE_ACCOUNT },
                    onLoginSuccess = { currentScreen = Screen.MAIN })
            }

            Screen.CREATE_ACCOUNT -> {
                CreateAccountScreen(
                    authViewModel = authViewModel,
                    onNavigateToLogin = { currentScreen = Screen.LOGIN },
                    onAccountCreated = { currentScreen = Screen.MAIN })
            }

            Screen.MAIN -> {
                MainScreen(authViewModel = authViewModel) {
                    currentScreen = Screen.LOGIN
                }
            }

            Screen.PAYMENT -> {
                PaymentScreenLauncher(onClick = {

                })
            }
        }
    }
}

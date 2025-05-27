package com.deepholistics

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.deepholistics.onboard.CreateAccountScreen
import com.deepholistics.onboard.LoginScreen
import com.deepholistics.onboard.MainScreen
import com.deepholistics.onboard.OnboardingScreen
import com.deepholistics.onboard.viewmodel.AuthViewModel
import com.deepholistics.onboard.viewmodel.OnboardingViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


enum class Screen {
    ONBOARDING, LOGIN, CREATE_ACCOUNT, MAIN
}

@Composable
fun App() {

    var currentScreen by remember { mutableStateOf(Screen.ONBOARDING) }
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
        }
    }
}

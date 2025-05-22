
package com.smitch.humantokenv2

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.smitch.humantokenv2.ui.*
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun App() {
    MaterialTheme {
        val navigator = rememberNavigator()
        
        NavHost(
            navigator = navigator,
            initialRoute = "/login"
        ) {
            scene("/login") {
                LoginScreen(
                    onNavigateToOtp = { navigator.navigate("/otp") }
                )
            }
            
            scene("/otp") {
                OtpScreen(
                    onNavigateToCreateAccount = { navigator.navigate("/create-account") }
                )
            }
            
            scene("/create-account") {
                CreateAccountScreen(
                    onAccountCreated = { navigator.navigate("/success") }
                )
            }
        }
    }
}

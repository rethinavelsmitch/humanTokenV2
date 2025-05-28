package com.deepholistics.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.deepholistics.onboard.helper.PrimaryButton
import com.deepholistics.onboard.helper.ScreenBackground
import com.deepholistics.onboard.viewmodel.AuthViewModel
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview()
@Composable
fun LoginScreenPreview() {
    // Provide a fake or mock AuthViewModel for preview purposes
    val fakeAuthViewModel = remember {
        object : AuthViewModel() {
            // Override necessary methods if required for preview
        }
    }

    LoginScreen(
        authViewModel = fakeAuthViewModel, onLoginSuccess = {})
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel, onLoginSuccess: () -> Unit,
) {

    val authState = authViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    var phoneNumber = remember { mutableStateOf("") }

    LaunchedEffect(authState.value.isAuthenticated) {

        println("authState.value.isAuthenticated ${authState.value.isAuthenticated}")

        if (authState.value.isAuthenticated) {
            onLoginSuccess()
        }
    }

    ScreenBackground {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login to your \n Human Token Dashboard",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                color = AppColors.TextPrimary,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            var phone: String = remember { mutableStateOf("").toString() }
            PhoneNumberInput(countryCode = "91", onCountryCodeClick = {
                // Show country code dropdown (custom)
            }, phoneNumber = "9159439327", onPhoneNumberChange = { phone = it })


            if (authState.value.error != null) {
                Text(
                    text = authState.value.error.toString(),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            PrimaryButton(
                modifier = Modifier.padding(vertical = AppDimens.paddingValues),
                buttonName = "Continue",
                onClick = {
                    scope.launch {
                        println("onclick called")

                        authViewModel.setAuthenticated(true)
//                    authViewModel.login(phonenumber.toString(), phonenumber.toString())
                    }
                },
                enable = true
            )
        }



        Spacer(modifier = Modifier.height(16.dp))


    }
}



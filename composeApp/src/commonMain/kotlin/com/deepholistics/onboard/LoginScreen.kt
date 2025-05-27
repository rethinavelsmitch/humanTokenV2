package com.deepholistics.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.deepholistics.onboard.helper.ScreenBackground
import com.deepholistics.onboard.viewmodel.AuthViewModel
import com.deepholistics.res.AppColors
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
        authViewModel = fakeAuthViewModel,
        onNavigateToCreateAccount = {},
        onLoginSuccess = {})
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel, onNavigateToCreateAccount: () -> Unit, onLoginSuccess: () -> Unit
) {

    val authState = authViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var isPasswordVisible = remember { mutableStateOf(false) }.value

    LaunchedEffect(authState.value.isAuthenticated) {
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

            OutlinedTextField(
                value = email.value.toString(),
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    TextButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Text(if (isPasswordVisible) "Hide" else "Show")
                    }
                },
                singleLine = true
            )

            if (authState.value.error != null) {
                Text(
                    text = authState.value.error.toString(),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Button(
                onClick = {
                    scope.launch {
                        authViewModel.login(email.toString(), password.toString())
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                enabled = !authState.value.isLoading && email.value.isNotEmpty() && password.value.isNotEmpty()
            ) {
                if (authState.value.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp), color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Sign In")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNavigateToCreateAccount) {
                Text("Don't have an account? Create one")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { /* Handle forgot password */ }) {
                Text("Forgot Password?")
            }
        }
    }
}



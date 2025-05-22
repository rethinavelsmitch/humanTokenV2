
package com.smitch.humantokenv2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OtpScreen(onNavigateToCreateAccount: () -> Unit) {
    var otp by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = otp,
            onValueChange = { 
                if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                    otp = it
                    isError = false
                }
            },
            label = { Text("Enter 6-digit OTP") },
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = {
                if (otp.length == 6) {
                    onNavigateToCreateAccount()
                } else {
                    isError = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Verify")
        }
    }
}

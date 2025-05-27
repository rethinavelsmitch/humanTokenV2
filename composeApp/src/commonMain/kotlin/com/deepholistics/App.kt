package com.deepholistics

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.deepholistics.ui.PaymentScreenLauncher
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        PaymentScreenLauncher()
    }
}
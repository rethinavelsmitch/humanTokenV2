package com.deepholistics.onboard.helper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.deepholistics.res.AppGradients
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview()
@Composable
fun CreateAccountScreenPreview() {
    ScreenBackground({})
}

@Composable
fun ScreenBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradients.diagonalBackgroundGradient)
    ) {
        content()
    }
}
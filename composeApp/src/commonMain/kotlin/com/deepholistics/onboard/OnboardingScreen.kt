package com.deepholistics.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.deepholistics.onboard.helper.ScreenBackground
import com.deepholistics.onboard.viewmodel.OnboardingViewModel
import com.deepholistics.res.AppColors
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(viewModel = OnboardingViewModel(), onCompleted = {})
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel, onCompleted: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isCompleted) {
        if (state.isCompleted) {
            onCompleted()
        }
    }


    ScreenBackground {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = viewModel.getCurrentPageTitle(),
                style = MaterialTheme.typography.headlineMedium,
                color = AppColors.TextPrimary,
                modifier = Modifier.padding(bottom = 32.dp)
            )

//            Image(
//                painter = painterResource("images/logo.png"),
//                contentDescription = null,
//                modifier = Modifier.semantics { contentDescription = "Right arrow" })
        }
    }
}

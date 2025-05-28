
package com.deepholistics.onboard.screens.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens.spacingLg
import com.deepholistics.res.AppDimens.spacingMd
import com.deepholistics.res.AppDimens.textSizeXl

@Composable
fun ChatAssistantScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = spacingMd)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(spacingLg)
    ) {
        Text(
            text = "Chat Assistant",
            color = AppColors.TextPrimary,
            fontSize = textSizeXl,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "Chat with our AI health assistant. Get instant answers to your health questions based on your biomarker results and health data.",
            color = AppColors.TextSecondary,
            fontSize = AppColors.TextSecondary
        )
        
        // Add chat assistant content here
    }
}


package com.deepholistics.onboard.screens.marketplace

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
fun MarketPlaceScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = spacingMd)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(spacingLg)
    ) {
        Text(
            text = "MarketPlace",
            color = AppColors.TextPrimary,
            fontSize = textSizeXl,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "Discover health products, supplements, and services tailored to your unique health profile and biomarker results.",
            color = AppColors.TextSecondary,
            fontSize = AppColors.TextSecondary
        )
        
        // Add marketplace content here
    }
}


package com.deepholistics.onboard.screens.biomarkers

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
import com.deepholistics.res.AppDimens
import com.deepholistics.res.AppDimens.spacingLg
import com.deepholistics.res.AppDimens.spacingMd
import com.deepholistics.res.AppDimens.textSizeXl

@Composable
fun BioMarkersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = spacingMd)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(spacingLg)
    ) {
        Text(
            text = "BioMarkers",
            color = AppColors.TextPrimary,
            fontSize = textSizeXl,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "View your comprehensive biomarker analysis. Track 100+ advanced biomarkers measuring everything from energy and mood-related markers to cancers, heart diseases and more.",
            color = AppColors.TextGrey,
            fontSize = AppDimens.textSizeMedium
        )
        
     }
}

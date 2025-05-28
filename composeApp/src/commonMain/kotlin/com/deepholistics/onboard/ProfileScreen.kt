
package com.deepholistics.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.deepholistics.onboard.helper.ScreenBackground
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens.spacingLg
import com.deepholistics.res.AppDimens.spacingMd
import com.deepholistics.res.AppDimens.textSizeXl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onNavigateBack: () -> Unit) {
    ScreenBackground {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Profile",
                            color = AppColors.TextPrimary,
                            fontSize = textSizeXl,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
//                            Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                                contentDescription = "Back",
//                                tint = AppColors.TextPrimary
//                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = AppColors.SurfaceDark
                    )
                )
            },
            containerColor = AppColors.Transparent
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = spacingMd)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(spacingLg)
            ) {
                Text(
                    text = "Profile Content Coming Soon...",
                    color = AppColors.TextPrimary,
                    fontSize = textSizeXl
                )
            }
        }
    }
}

package com.deepholistics.onboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepholistics.res.AppColors
import com.deepholistics.res.Dimens
import com.deepholistics.res.TextSizes
import humantokenv2.composeapp.generated.resources.Res
import humantokenv2.composeapp.generated.resources.access_dashboard
import humantokenv2.composeapp.generated.resources.app_name
import humantokenv2.composeapp.generated.resources.at_home_blood_draw
import humantokenv2.composeapp.generated.resources.back
import humantokenv2.composeapp.generated.resources.blood_draw_desc
import humantokenv2.composeapp.generated.resources.continue_to_pay
import humantokenv2.composeapp.generated.resources.dashboard_desc
import humantokenv2.composeapp.generated.resources.ht_logo_196
import humantokenv2.composeapp.generated.resources.next_steps
import humantokenv2.composeapp.generated.resources.payment_confirmation
import humantokenv2.composeapp.generated.resources.payment_confirmation_desc
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreenLauncher() {
    // Use theme colors
    val backgroundColor = AppColors.BackgroundDark
    val surfaceColor = AppColors.SurfaceDark
    val cardBackgroundColor = AppColors.CardBackground
    val primaryColor = AppColors.Primary
    val textPrimaryColor = AppColors.TextPrimary
    val textSecondaryColor = AppColors.TextSecondary


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(Res.string.app_name),
                            color = textPrimaryColor,
                            fontSize = TextSizes.sp_20,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(
                            painter = painterResource(Res.drawable.ht_logo_196),
                            contentDescription = stringResource(Res.string.back),
                            tint = textPrimaryColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = surfaceColor
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = surfaceColor,
                shadowElevation = 8.dp
            ) {
                Button(
                    onClick = { /* Handle payment */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.dp_56)
                        .padding(Dimens.dp_16),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor,
                        contentColor = textPrimaryColor
                    ),
                    shape = RoundedCornerShape(Dimens.dp_8)
                ) {
                    Text(
                        text = stringResource(Res.string.continue_to_pay),
                        fontSize = TextSizes.sp_14,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Dimens.dp_16)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Dimens.dp_24)
        ) {
            Spacer(modifier = Modifier.height(Dimens.dp_24))

            Text(
                text = stringResource(Res.string.next_steps),
                fontSize = TextSizes.sp_28,
                fontWeight = FontWeight.Bold,
                color = textPrimaryColor
            )

            Spacer(modifier = Modifier.height(Dimens.dp_16))

            StepCard(
                icon = painterResource(Res.drawable.ht_logo_196),
                title = stringResource(Res.string.payment_confirmation),
                description = stringResource(Res.string.payment_confirmation_desc),
                backgroundColor = cardBackgroundColor,
                textPrimaryColor = textPrimaryColor,
                textSecondaryColor = textSecondaryColor,
            )

            StepCard(
                icon = painterResource(Res.drawable.ht_logo_196),
                title = stringResource(Res.string.at_home_blood_draw),
                description = stringResource(Res.string.blood_draw_desc),
                backgroundColor = cardBackgroundColor,
                textPrimaryColor = textPrimaryColor,
                textSecondaryColor = textSecondaryColor,
            )

            StepCard(
                icon = painterResource(Res.drawable.ht_logo_196),
                title = stringResource(Res.string.access_dashboard),
                description = stringResource(Res.string.dashboard_desc),
                backgroundColor = cardBackgroundColor,
                textPrimaryColor = textPrimaryColor,
                textSecondaryColor = textSecondaryColor,
            )

            Spacer(modifier = Modifier.height(Dimens.dp_32))
        }
    }
}

@Composable
private fun StepCard(
    title: String,
    description: String,
    backgroundColor: Color,
    textPrimaryColor: Color,
    textSecondaryColor: Color,
    icon: Painter,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(Dimens.dp_12),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.dp_16),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(Dimens.dp_32),
                tint = textPrimaryColor
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    fontSize = TextSizes.sp_20,
                    fontWeight = FontWeight.Bold,
                    color = textPrimaryColor
                )

                Text(
                    text = description,
                    fontSize = TextSizes.sp_14,
                    color = textSecondaryColor,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun PaymentScreenLauncherPreview() {
    PaymentScreenLauncher()
}

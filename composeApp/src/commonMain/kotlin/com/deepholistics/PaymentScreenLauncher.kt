package com.deepholistics

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val backgroundColor = BackgroundDark
    val surfaceColor = SurfaceDark
    val cardBackgroundColor = CardBackground
    val primaryColor = Primary
    val textPrimaryColor = TextPrimary
    val textSecondaryColor = TextSecondary

    // Define dimensions (simulated from dimens.xml)
    val spacingMd = 16.dp
    val spacingLg = 24.dp
    val spacingXl = 32.dp
    val cardCornerRadius = 12.dp
    val cardPadding = 16.dp
    val buttonHeight = 56.dp
    val buttonCornerRadius = 8.dp
    val buttonMargin = 16.dp
    val iconSizeMd = 32.dp
    val textSizeTitle = 28.sp
    val textSizeXl = 20.sp
    val textSizeMd = 14.sp

    Spacer(modifier = Modifier.height(spacingLg))

    Text(
        text = stringResource(Res.string.next_steps),
        fontSize = textSizeTitle,
        fontWeight = FontWeight.Bold,
        color = textPrimaryColor
    )

    Spacer(modifier = Modifier.height(spacingMd))

    StepCard(
        icon = painterResource(Res.drawable.ht_logo_196),
        title = stringResource(Res.string.payment_confirmation),
        description = stringResource(Res.string.payment_confirmation_desc),
        backgroundColor = cardBackgroundColor,
        textPrimaryColor = textPrimaryColor,
        textSecondaryColor = textSecondaryColor,
        cardPadding = cardPadding,
        cardCornerRadius = cardCornerRadius,
        iconSize = iconSizeMd,
        titleSize = textSizeXl,
        descriptionSize = textSizeMd
    )

    StepCard(
        icon = painterResource(Res.drawable.ht_logo_196),
        title = stringResource(Res.string.at_home_blood_draw),
        description = stringResource(Res.string.blood_draw_desc),
        backgroundColor = cardBackgroundColor,
        textPrimaryColor = textPrimaryColor,
        textSecondaryColor = textSecondaryColor,
        cardPadding = cardPadding,
        cardCornerRadius = cardCornerRadius,
        iconSize = iconSizeMd,
        titleSize = textSizeXl,
        descriptionSize = textSizeMd
    )

    StepCard(
        icon = painterResource(Res.drawable.ht_logo_196),
        title = stringResource(Res.string.access_dashboard),
        description = stringResource(Res.string.dashboard_desc),
        backgroundColor = cardBackgroundColor,
        textPrimaryColor = textPrimaryColor,
        textSecondaryColor = textSecondaryColor,
        cardPadding = cardPadding,
        cardCornerRadius = cardCornerRadius,
        iconSize = iconSizeMd,
        titleSize = textSizeXl,
        descriptionSize = textSizeMd
    )

    Spacer(modifier = Modifier.height(spacingXl))
}


@Composable
private fun StepCard(
    title: String,
    description: String,
    backgroundColor: Color,
    textPrimaryColor: Color,
    textSecondaryColor: Color,
    cardPadding: Dp,
    cardCornerRadius: Dp,
    iconSize: Dp,
    titleSize: TextUnit,
    descriptionSize: TextUnit,
    icon: Painter,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(cardCornerRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(cardPadding),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = textPrimaryColor
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    fontSize = titleSize,
                    fontWeight = FontWeight.Bold,
                    color = textPrimaryColor
                )

                Text(
                    text = description,
                    fontSize = descriptionSize,
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

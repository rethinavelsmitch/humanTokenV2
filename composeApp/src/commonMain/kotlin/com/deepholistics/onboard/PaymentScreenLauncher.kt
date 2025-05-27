package com.deepholistics.onboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.deepholistics.onboard.helper.ScreenBackground
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppColors.PurpleBackground
import com.deepholistics.res.AppFonts
import com.deepholistics.res.Dimens
import com.deepholistics.res.Dimens.dp_1
import com.deepholistics.res.TextSizes
import humantokenv2.composeapp.generated.resources.Res
import humantokenv2.composeapp.generated.resources.access_dashboard
import humantokenv2.composeapp.generated.resources.at_home_blood_draw
import humantokenv2.composeapp.generated.resources.blood_draw_desc
import humantokenv2.composeapp.generated.resources.dashboard_desc
import humantokenv2.composeapp.generated.resources.ic_payment
import humantokenv2.composeapp.generated.resources.ic_puzzle
import humantokenv2.composeapp.generated.resources.ic_tag
import humantokenv2.composeapp.generated.resources.next_steps
import humantokenv2.composeapp.generated.resources.payment_confirmation
import humantokenv2.composeapp.generated.resources.payment_confirmation_desc
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PaymentScreenLauncher() {
    ScreenBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.dp_16)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Dimens.dp_24)
        ) {
            Spacer(modifier = Modifier.height(Dimens.dp_56))

            Text(
                text = stringResource(Res.string.next_steps),
                fontSize = TextSizes.sp_28,
                fontFamily = AppFonts.medium(),
                color = AppColors.White
            )

            Spacer(modifier = Modifier.height(Dimens.dp_16))

            StepCard(
                icon = painterResource(Res.drawable.ic_payment),
                title = stringResource(Res.string.payment_confirmation),
                description = stringResource(Res.string.payment_confirmation_desc),
            )

            StepCard(
                icon = painterResource(Res.drawable.ic_tag),
                title = stringResource(Res.string.at_home_blood_draw),
                description = stringResource(Res.string.blood_draw_desc),
            )

            StepCard(
                icon = painterResource(Res.drawable.ic_puzzle),
                title = stringResource(Res.string.access_dashboard),
                description = stringResource(Res.string.dashboard_desc),
            )
        }
    }
}

@Composable
private fun StepCard(
    title: String,
    description: String,
    icon: Painter,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PurpleBackground),
        shape = RoundedCornerShape(Dimens.dp_12),
        border = BorderStroke(width = dp_1, color = AppColors.BorderLineColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.dp_16),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            Column(
                modifier = Modifier.size(Dimens.dp_32).background(
                    color = AppColors.PurpleDarkColor,
                    shape = RoundedCornerShape(Dimens.dp_12)
                ).border(
                    width = dp_1,
                    color = AppColors.BorderLineColor,
                    shape = RoundedCornerShape(Dimens.dp_12)
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(Dimens.dp_20),
                    painter = icon,
                    contentDescription = "icon",
                )
            }


            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    fontSize = TextSizes.sp_20,
                    fontFamily = AppFonts.medium(),
                    color = AppColors.White
                )

                Text(
                    text = description,
                    fontSize = TextSizes.sp_14,
                    fontFamily = AppFonts.regular(),
                    color = AppColors.TextGrey,
                    lineHeight = TextSizes.sp_20
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

package com.deepholistics.onboard.helper

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppDimens

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    buttonName: String,
    enable: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        enabled = enable,
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(height = AppDimens.buttonHeight),
        colors = ButtonColors(
            containerColor = AppColors.CardBackground,
            contentColor = AppColors.TextPrimary,
            disabledContentColor = AppColors.SurfaceDark,
            disabledContainerColor = AppColors.BackgroundDark,
        ),
        shape = RoundedCornerShape(AppDimens.cardCornerRadius)
    ) {
        Text(
            text = buttonName,
            fontSize = AppDimens.textSizeMedium, //fontDimensionResource(id = R.dimen._18sp),
            //   fontFamily = bold,
            color = AppColors.TextPrimary
        )
    }
}
package com.deepholistics.utils.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.deepholistics.res.AppColors
import com.deepholistics.res.AppFonts
import com.deepholistics.res.Dimens
import com.deepholistics.res.TextSizes


@Composable
fun PrimaryButton(onClick: () -> Unit, buttonName: String, isEnable: Boolean = true) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(Dimens.dp_12),
        enabled = isEnable,
        colors = ButtonColors(
            containerColor = AppColors.PurpleButtonBackground,
            contentColor = AppColors.PurpleButtonBackground,
            disabledContainerColor = AppColors.PurpleButtonBackground,
            disabledContentColor = AppColors.PurpleButtonBackground
        ),
        modifier = Modifier.height(height = Dimens.dp_50)
    ) {
        Text(
            text = buttonName,
            fontSize = TextSizes.sp_16,
            fontFamily = AppFonts.medium(),
            color = AppColors.White
        )
    }
}

package com.deepholistics.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.deepholistics.R



@Composable
fun Modifier.onTextClick(rippleEffect: Boolean = false, onClick: () -> Unit): Modifier {
    return this.clickable(
        onClick = { onClick() }, indication = if (rippleEffect) {
            ripple(
                bounded = true,
                color = colorResource(id = R.color.colorPrimaryDarkAccent),
            )
        } else {
            null
        }, interactionSource = remember { MutableInteractionSource() })
}


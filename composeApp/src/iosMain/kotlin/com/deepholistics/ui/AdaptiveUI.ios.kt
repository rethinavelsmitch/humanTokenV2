package com.deepholistics.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitViewController
import com.deepholistics.LocalNativeViewFactory
import com.deepholistics.res.Dimens

@Composable
actual fun CommonButton(modifier: Modifier, onClick: () -> Unit) {
    val view = LocalNativeViewFactory.current
    UIKitViewController(modifier = modifier.height(Dimens.dp_50).width(Dimens.dp_100), factory = {
        view.createButtonView(label = "Button", onClick = onClick)
    })
}
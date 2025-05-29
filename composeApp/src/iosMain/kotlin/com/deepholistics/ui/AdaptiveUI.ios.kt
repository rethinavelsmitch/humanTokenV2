package com.deepholistics.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@Composable
actual fun ShowDatePicker(
    modifier: Modifier,
    selectedDate: String,
    onDismiss: () -> Unit,
    onDateSelected: (String) -> Unit,
) {
    val view = LocalNativeViewFactory.current
    UIKitViewController(modifier = modifier.height(Dimens.dp_160).fillMaxWidth(), factory = {
        view.showDatePicker(
            selectedDate = selectedDate,
            onDismiss = onDismiss,
            onDateSelected = onDateSelected
        )
    })
}

//@Composable
//actual fun ShowAlertDialog(
//    modifier: Modifier,
//    title: String,
//    message: String,
//    onDismiss: () -> Unit
//) {
//    val view = LocalNativeViewFactory.current
//    UIKitViewController(modifier = modifier.height(Dimens.dp_200).fillMaxWidth(), factory = {
//        view.showAlertDialog(primaryText = title, secondaryText = message, onDismiss = onDismiss)
//    }
//    )
//}

@Composable
actual fun ShowAlertDialog(
    modifier: Modifier,
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    val view = LocalNativeViewFactory.current

    LaunchedEffect(Unit) {
        view.showAlertDialog(
            primaryText = title,
            secondaryText = message,
            onDismiss = onDismiss
        )
    }
}

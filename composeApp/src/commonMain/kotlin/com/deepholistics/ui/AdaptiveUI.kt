package com.deepholistics.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CommonButton(modifier: Modifier = Modifier, onClick: () -> Unit)

@Composable
expect fun ShowDatePicker(
    modifier: Modifier = Modifier,
    selectedDate: String,
    onDismiss: () -> Unit,
    onDateSelected: (String) -> Unit,
)

@Composable
expect fun ShowAlertDialog(modifier: Modifier, title: String, message: String, onDismiss: () -> Unit, onLogout: () -> Unit)
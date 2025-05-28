package com.deepholistics.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CommonButton(modifier: Modifier = Modifier, onClick: () -> Unit)
package com.deepholistics.res

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object AppGradients {
    val diagonalBackgroundGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF371D55), // rgba(55, 29, 85, 1.0)
            Color(0xFF440A29)  // rgba(68, 10, 41, 1.0)
        ),
        start = androidx.compose.ui.geometry.Offset.Zero,
        end = androidx.compose.ui.geometry.Offset.Infinite
    )
}
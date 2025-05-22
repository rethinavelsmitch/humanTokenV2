
package com.smitch.humantokenv2

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Authentication App"
    ) {
        App()
    }
}

package com.deepholistics

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.window.ComposeUIViewController
import com.deepholistics.onboard.App

val LocalNativeViewFactory = staticCompositionLocalOf<NativeViewFactory> {
    error("No view factory")
}


fun MainViewController(
    nativeViewFactory: NativeViewFactory,
) = ComposeUIViewController {
    CompositionLocalProvider(LocalNativeViewFactory provides nativeViewFactory) {
        App()
    }
}

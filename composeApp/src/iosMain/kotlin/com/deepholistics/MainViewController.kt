package com.deepholistics

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.window.ComposeUIViewController
import com.deepholistics.data.networking.createHttpClient
import com.deepholistics.onboard.App
import io.ktor.client.engine.darwin.Darwin

val LocalNativeViewFactory = staticCompositionLocalOf<NativeViewFactory> {
    error("No view factory")
}


fun MainViewController(
    nativeViewFactory: NativeViewFactory,
) = ComposeUIViewController {
    CompositionLocalProvider(LocalNativeViewFactory provides nativeViewFactory) {
        val darwinClient = remember {
            createHttpClient(Darwin.create())
        }
        App(darwinClient)
    }
}

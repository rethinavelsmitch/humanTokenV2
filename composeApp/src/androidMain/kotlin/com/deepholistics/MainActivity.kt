package com.deepholistics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.deepholistics.data.networking.createHttpClient
import com.deepholistics.onboard.App
import io.ktor.client.engine.okhttp.OkHttp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val httpClient= remember {
                createHttpClient(OkHttp.create())
            }
            App(httpClient)
        }
    }
}

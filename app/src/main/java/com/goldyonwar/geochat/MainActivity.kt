package com.goldyonwar.geochat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.goldyonwar.geochat.ui.navigation.AppNavHost
import com.goldyonwar.geochat.ui.theme.GeoChatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            GeoChatTheme {
                AppNavHost()
            }
        }
    }
}

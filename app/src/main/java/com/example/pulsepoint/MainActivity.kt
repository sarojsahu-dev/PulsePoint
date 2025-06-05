package com.example.pulsepoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pulsepoint.presentation.ui.HopeNearbyInputContainer
import com.example.pulsepoint.presentation.ui.HopeNearbySuccessContainer
import com.example.pulsepoint.presentation.ui.LaunchScreen
import com.example.pulsepoint.style.Purple100
import com.example.pulsepoint.style.Purple200
import com.example.pulsepoint.ui.theme.PulsePointTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Purple100.toArgb()

        installSplashScreen()

        setContent {
            Scaffold { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    LaunchScreen()
                }
            }
        }
    }
}

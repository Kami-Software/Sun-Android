package com.example.sun_android

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.sun_android.bottomnav.CustomBottomNavigation
import com.example.sun_android.bottomnav.HabbitScreen
import com.example.sun_android.bottomnav.Screen
import com.example.sun_android.ui.theme.SunAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            window.statusBarColor = Color.Black.toArgb()
            window.navigationBarColor = Color.Black.toArgb()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController?.setSystemBarsAppearance(
                    0, // Clear appearance
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = 0 // Clear appearance
            }

            val currentScreen = remember { mutableStateOf<Screen>(Screen.Home) }

            SunAndroidTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black), color = Color.Black
                ) {
                    Scaffold(
                        containerColor = Color.Black,
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            CustomBottomNavigation(currentScreenId = currentScreen.value.id) { screen ->
                                currentScreen.value = screen
                            }
                        }
                    ) { innerPadding ->
                        when (currentScreen.value) {
                            Screen.Home -> Text(
                                text = "Welcome to Home",
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize(),
                                color = Color.White
                            )
                            Screen.Habits -> HabbitScreen(currentScreen.value) { screen ->
                                currentScreen.value = screen
                            }
                            else -> Text(
                                text = "Other Screen",
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize(),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

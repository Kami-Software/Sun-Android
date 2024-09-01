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
import androidx.compose.ui.tooling.preview.Preview
import com.example.sun_android.bottomnav.CustomBottomNavigation
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
                    0, // Temizle
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = 0 // Temizle
            }

            // Geçerli ekran durumunu yönetin
            val currentScreen = remember { mutableStateOf<Screen>(Screen.Home) }

            SunAndroidTheme {
                Surface( modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),color = Color.Black) { // Arka plan rengini siyah yap
                    Scaffold(containerColor = Color.Black, modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            CustomBottomNavigation(currentScreenId = currentScreen.value.id) { screen ->
                                currentScreen.value = screen
                            }
                        }
                    ) { innerPadding ->
                        // Ana içerik alanı
                        Text(
                            text = "Welcome to ${currentScreen.value.title}",
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            color = Color.White // Metin rengini beyaz yaparak okunabilirliği artırın
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Black) { // Arka plan rengini siyah yap
        Text(
            text = "Hello $name!",
            modifier = modifier,
            color = Color.White // Metin rengini beyaz yaparak okunabilirliği artırın
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SunAndroidTheme {
        Greeting("Android")
    }
}

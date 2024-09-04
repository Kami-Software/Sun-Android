package com.example.sun_android

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sun_android.bottomnav.CustomBottomNavigation
import com.example.sun_android.sun.presentation.habits_screen.HabbitsScreen
import com.example.sun_android.sun.presentation.statistics_screen.StatisticsScreen
import com.example.sun_android.sun.presentation.swipe_screen.SwipeScreen
import com.example.sun_android.sun.util.Screens
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

            val currentScreen = remember { mutableStateOf<Screens>(Screens.Home) }

            SunAndroidTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black), color = Color.Black
                ) {
                    val navController = rememberNavController()

                    Scaffold(
                        containerColor = Color.Black,
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            CustomBottomNavigation(currentScreenId = currentScreen.value.id) { screen ->
                                currentScreen.value = screen
                            }
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding)
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            NavigationHost(navController = navController)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.HabbitsScreen.id) {
        composable(route = Screens.SwipeScreen.id) {
            SwipeScreen(navController = navController)
        }
        composable(route = Screens.StatisticsScreen.id) {
            StatisticsScreen(navController = navController)
        }
        composable(route = Screens.HabbitsScreen.id) {
            HabbitsScreen(navController = navController)
        }
    }
}
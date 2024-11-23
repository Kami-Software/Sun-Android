package com.example.sun_android

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sun_android.sun.presentation.navigation.CustomBottomNavigation
import com.example.sun_android.sun.presentation.habits_screen.HabbitsScreen
import com.example.sun_android.sun.presentation.statistics_screen.StatisticsScreen
import com.example.sun_android.sun.presentation.todos_screen.TodosScreen
import com.example.sun_android.sun.util.Screens
import com.example.sun_android.ui.theme.SunAndroidTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

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
            // Bugünün tarihini al
            val currentDate = LocalDate.now()
            // Günün tam ismini almak için formatla
            val dayOfWeek = currentDate.format(DateTimeFormatter.ofPattern("EEEE"))
            val currentScreen = remember { mutableStateOf<Screens>(Screens.HabbitsScreen) }

            SunAndroidTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    LaunchedEffect(navController) {
                        navController.currentBackStackEntryFlow.collect { backStackEntry ->
                            val destination = backStackEntry.destination.route
                            currentScreen.value = when (destination) {
                                Screens.HabbitsScreen.id -> Screens.HabbitsScreen
                                Screens.TodosScreen.id -> Screens.TodosScreen
                                Screens.StatisticsScreen.id -> Screens.StatisticsScreen
                                else -> Screens.HabbitsScreen
                            }
                        }
                    }
                    Scaffold(
                        containerColor = Color.Black,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(WindowInsets.systemBars.asPaddingValues()),
                        topBar = {
                            Row(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    )
                                    .height(50.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween // İki öğe arasında boşluk oluştur

                            ) {
                                Column {
                                    Row {
                                        Text(
                                            text = "Hello, Ovina",
                                            color = Color.White,
                                            fontSize = 24.sp,
                                            modifier = Modifier.padding(end = 3.dp)
                                        )
                                        Text(
                                            text = "\uD83D\uDC4B",
                                            color = Color.White,
                                            fontSize = 24.sp,
                                            modifier = Modifier.padding()
                                        )
                                    }
                                    Row {
                                        Text(
                                            text = "$dayOfWeek",
                                            color = Color.Gray,
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding(end = 3.dp)
                                        )
                                        Text(
                                            text = "10 Habits",
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            modifier = Modifier.padding()
                                        )
                                    }
                                }
                                Row {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .clip(RoundedCornerShape(30.dp)) // Köşeleri yuvarla
                                            .background(Color(0xFF2c292a)) // Arka plan rengi
                                            .border(
                                                1.dp, Color(0xFF6c6565), RoundedCornerShape(30.dp),
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        Text(
                                            text = "100 \uD83D\uDD25",
                                            modifier = Modifier
                                                .align(Alignment.Center)
                                                .padding(horizontal = 15.dp),
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold,

                                            )
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    IconButton(
                                        onClick = {},
                                        modifier = Modifier
                                            .size(50.dp)
                                            .clip(shape = CircleShape)
                                            .background(Color(0xFF2c292a))
                                            .border(1.dp, Color(0xFF6c6565), CircleShape) // Çerçeve eklendi
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Settings,
                                            contentDescription = "Icon Button",
                                            modifier = Modifier.size(35.dp),
                                            tint = Color.White
                                        )

                                    }
                                }

                            }
                        },
                        bottomBar = {
                            CustomBottomNavigation(currentScreenId = currentScreen.value.id) { screen ->
                                currentScreen.value = screen
                                navController.navigate(screen.id) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .padding(innerPadding)
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
        composable(route = Screens.TodosScreen.id) {
            TodosScreen(navController = navController)
        }
        composable(route = Screens.StatisticsScreen.id) {
            StatisticsScreen(navController = navController)
        }
        composable(route = Screens.HabbitsScreen.id) {
            HabbitsScreen(navController = navController)
        }
    }
}
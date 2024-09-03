package com.example.sun_android.bottomnav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sun_android.HabbitCard
import com.example.sun_android.bottomnav.CustomBottomNavigation
import com.example.sun_android.bottomnav.Screen

@Composable
fun HabbitScreen(currentScreen: Screen, onScreenSelected: (Screen) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            CustomBottomNavigation(currentScreenId = currentScreen.id) { screen ->
                onScreenSelected(screen)
            }
        }
    ) { innerPadding ->
        HabbitCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
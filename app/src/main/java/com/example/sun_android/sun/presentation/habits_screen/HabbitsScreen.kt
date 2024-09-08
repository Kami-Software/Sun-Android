package com.example.sun_android.sun.presentation.habits_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sun_android.sun.presentation.components.HabbitCard

@Composable
fun HabbitsScreen(navController: NavController) {
    val habits = remember { listOf("Running") }

    // Main layout for the Habits screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Your Habits",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // LazyVerticalStaggeredGrid to display habits
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2), // Two columns
            modifier = Modifier.fillMaxSize()
        ) {
            items(habits.size) { index ->
                HabbitCard(habits[index])
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HabbitScreenPreview() {
    HabbitsScreen(navController = NavController(LocalContext.current))
}

package com.example.sun_android.sun.presentation.habits_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sun_android.HabbitCard

@Composable
fun HabbitsScreen(navController: NavController) {
    // Simulated list of habits (Can be replaced with actual data)
    val habits = listOf("Running", "Meditation", "Reading", "Exercise", "Coding", "Yoga", "Writing")

    // Main layout for the Habbits screen
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

        // Yüksekliği sınırlıyoruz
        Box(
            modifier = Modifier
                .fillMaxSize()
            // Yüksekliği sabitliyoruz
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2), // İki sütun halinde grid
                modifier = Modifier.fillMaxSize() // Tüm alanı dolduruyor
            ) {
                items(habits.size) { index ->
                    HabbitCard(habits[index])
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HabbitScreenPreview() {
    HabbitsScreen(navController = NavController(LocalContext.current))

}
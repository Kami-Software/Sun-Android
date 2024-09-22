package com.example.sun_android.sun.presentation.habits_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalActivity
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sun_android.sun.presentation.components.HabbitCard

@Composable
fun HabbitsScreen(navController: NavController, viewModel: HabitViewModel = hiltViewModel()) {
    val habits = remember { listOf("Running") }
    val habitsState = viewModel.habits.collectAsState()

    // Main layout for the Habits screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // LazyVerticalStaggeredGrid to display habits
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2), // Two columns
            modifier = Modifier.fillMaxSize()
        ) {
            val habits = habitsState.value // Get the current state value
            items(habits.size) { index ->
                val habit = habits[index]
                HabbitCard(title = habit.title) // Display the habit title
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HabbitScreenPreview() {
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
                    text = "Sunday",
                    color = Color(0xFFB0B0B0),
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
}

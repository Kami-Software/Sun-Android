package com.example.sun_android.sun.presentation.statistics_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.outlined.ChangeCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sun_android.sun.presentation.components.CustomExpandableFab
import com.example.sun_android.sun.presentation.components.FABItem

@Composable
fun StatisticsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        CustomExpandableFab(
            items = listOf(
                FABItem(icon = Icons.Default.CheckCircleOutline, text = "Add Todo"),
                FABItem(icon = Icons.Outlined.ChangeCircle, text = "Add Habit"),
            ),
            onItemClick = { clickedItem ->
                Log.e("Fab button","Clicked on: ${clickedItem.text}")
            }
        )
        Text(
            text = "Your Statistics",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}
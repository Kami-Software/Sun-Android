package com.example.sun_android.sun.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ChangeCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed class Screens(
    val id: String,
    val title: String,
    val icon: ImageVector,
) {

    object HabbitsScreen : Screens("habbit", "Habits", Icons.Outlined.ChangeCircle)
    object TodosScreen : Screens("todos", "Todos", Icons.Default.CheckCircleOutline)
    object StatisticsScreen : Screens("statistics", "Statistics", Icons.Outlined.PieChart)

}

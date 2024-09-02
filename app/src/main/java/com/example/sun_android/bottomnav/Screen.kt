package com.example.sun_android.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PieChart
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val id:String,
    val title:String,
    val icon:ImageVector,
){



    object Home:Screen("home","Home",Icons.Outlined.Add)
    object Habits:Screen("seacrh","Habits",Icons.Outlined.Home)
    object Swipe:Screen("profile","Swipe",Icons.Default.Style)
    object Statistics:Screen("settings","Statistics",Icons.Outlined.PieChart)

    object Items{
        val list= listOf(9*+
            Home,Habits,Swipe,Statistics
        )
    }

}
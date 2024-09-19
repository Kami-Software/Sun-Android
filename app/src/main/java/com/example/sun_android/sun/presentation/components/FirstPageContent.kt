package com.example.sun_android.sun.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddToQueue
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.outlined.AirplaneTicket
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FirstPageContent(
    habitName: String,
    onHabitNameChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.AirplaneTicket,
            contentDescription = "Icon Button",
            modifier = Modifier.size(100.dp),
            tint = Color(0xFFec3557)
        )

        Text(
            text = "Habit Name",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Habit Name",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        TextField(
            value = habitName,
            onValueChange = onHabitNameChange,
            label = { Text("Swimming") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(Color.Red)
        )


    }
}

@Composable
@Preview
fun FirstPageContentPreview() {
    FirstPageContent(
        habitName = "",
        onHabitNameChange = {}
    )
}
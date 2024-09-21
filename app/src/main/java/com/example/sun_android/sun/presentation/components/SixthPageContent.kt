package com.example.sun_android.sun.presentation.components

import android.widget.TimePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.EventRepeat
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SixthPageContent() {
    var checked by remember { mutableStateOf(true) }
    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.AccessTime,
            contentDescription = "Icon Button",
            modifier = Modifier.size(100.dp),
            tint = Color(0xFFec3557)
        )

        Text(
            modifier = Modifier.padding(top = 15.dp),
            text = "Reminder",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Set a reminder for your habit",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFB0B0B0), // Lighter gray for subtitle
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically // Align items vertically in the center

        ) {
            Text(
                text = "Add Reminder",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White, // Lighter gray for subtitle
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFFec3557),
                )
            )
        }
        if (checked){
            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically // Align items vertically in the center

            ) {
                Text(
                    text = "Reminder Time:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White, // Lighter gray for subtitle
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1f)
                )


            }
        }

    }
}

@Composable
@Preview
fun SixthPageContentView() {
    SixthPageContent()
}
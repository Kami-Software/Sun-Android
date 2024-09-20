package com.example.sun_android.sun.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ThirdPageContent() {
    val weekDays = listOf("M", "T", "W", "T", "F", "S", "S")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.CalendarMonth,
            contentDescription = "Icon Button",
            modifier = Modifier.size(100.dp),
            tint = Color(0xFFec3557)
        )

        Text(
            modifier = Modifier.padding(top = 15.dp),
            text = "Frequency",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Choose which days to repeat this habit",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFB0B0B0), // Lighter gray for subtitle
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        Text(
            text = "Repeat",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White, // Lighter gray for subtitle
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 20.dp, start = 20.dp).align(Alignment.Start)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            for (i in 1..7) {
                Box(
                    modifier = Modifier // Sağ üst köşeden biraz boşluk bırakma
                        .size(35.dp) // Şeklin boyutu
                        .clip(CircleShape)
                        .background(Color.Gray),
                ) {
                    Text(
                        text = weekDays[i - 1],
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                }
            }
        }


    }
}

@Composable
@Preview
fun ThirdPageContentView(){
    ThirdPageContent()
}
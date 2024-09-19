package com.example.sun_android.sun.presentation.components

import androidx.compose.material3.TextField
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
import androidx.compose.material.icons.outlined.AirplaneTicket
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Second page content
@Composable
fun SecondPageContent(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Palette,
            contentDescription = "Icon Button",
            modifier = Modifier.size(100.dp),
            tint = Color(0xFFec3557)
        )

        Text(
            modifier = Modifier.padding(top = 15.dp),
            text = "Emoji And Color",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        // Subtitle Text
        Text(
            text = "Choose an emoji and a color to your habit",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFB0B0B0), // Lighter gray for subtitle
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        ColorCard()
    }
}

@Preview
@Composable
fun SecondPageContentPreview() {
    SecondPageContent(
        )
}
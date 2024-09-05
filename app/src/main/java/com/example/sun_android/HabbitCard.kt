package com.example.sun_android

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwitchCamera
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HabbitCard(title: String) {
    val weekDays = listOf("M", "T", "W", "T", "F", "S", "S")
    var progress by remember { mutableStateOf(0.0f) }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
            },
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .height(175.dp)
                    .background(Color.Black)
            ) {
                Column {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .size(24.dp) // Şeklin boyutu
                                .clip(CircleShape)
                                .background(Color.Red) // Şeklin rengi
                                .align(Alignment.TopEnd) // Sağ üst köşeye yerleştirme
                                .padding(8.dp)
                                .clickable {
                                    if (progress < 1.0f) {
                                        progress += 0.1f
                                    }
                                }// Sağ üst köşeden biraz boşluk bırakma
                        )
                        Box(
                            contentAlignment = Alignment.Center, // Center the icon in the middle
                            modifier = Modifier
                                .size(80.dp)
                        ) {
                            CircularProgressIndicator(
                                progress = progress, // Bind this to your progress state
                                modifier = Modifier
                                    .fillMaxSize(), // Adjust padding to control distance between circle and progress indicator
                                color = Color.Blue, // Color of the progress indicator
                                strokeWidth = 4.dp // Thickness of the progress indicator
                            )
                            Box(
                                modifier = Modifier
                                    .size(60.dp) // Şeklin boyutu
                                    .clip(CircleShape)
                                    .background(Color.Red) // Şeklin rengi
                            ) {
                                Icon(
                                    imageVector = Icons.Default.SwitchCamera,
                                    contentDescription = "none",
                                    Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                    Text(text = title, color = Color.White)
                    Row {
                        for (i in 1..7) {
                            Box(
                                modifier = Modifier
                                    .padding(2.dp) // Sağ üst köşeden biraz boşluk bırakma
                                    .size(16.dp) // Şeklin boyutu
                                    .clip(CircleShape)
                                    .background(Color.Red) // Şeklin rengi
                            ) {
                                Text(
                                    text = weekDays[i - 1],
                                    color = Color.White,
                                    modifier = Modifier.align(Alignment.Center),
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                }
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun HabbitCardPreview() {
    HabbitCard("Modifier")
}
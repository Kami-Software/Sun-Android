package com.example.sun_android.sun.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.SwitchCamera
import androidx.compose.material.icons.outlined.Circle
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HabbitCard(title: String) {
    val weekDays = listOf("M", "T", "W", "T", "F", "S", "S")
    var progress by remember { mutableStateOf(0.0f) }
    val isCompleted = progress >= 1.0f

    Card(
        modifier = Modifier
            .padding(4.dp)
            .width(225.dp)
            .height(225.dp)
            .border(0.5.dp, Color.Red, MaterialTheme.shapes.medium)
            .clickable {
            }, shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted) Color.Red.copy(alpha = 0.2f) else Color.Gray.copy(
                alpha = 0.1f
            )

        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .size(30.dp) // Şeklin boyutu
                                .clip(CircleShape)
                                .align(Alignment.TopEnd) // Sağ üst köşeye yerleştirme
                                .clickable {
                                    if (progress < 1.0f) {
                                        progress += 0.1f
                                    }
                                }// Sağ üst köşeden biraz boşluk bırakma
                        ) {
                            Icon(
                                imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Outlined.Circle,
                                contentDescription = null,
                                tint = Color.Red,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Box(
                            contentAlignment = Alignment.Center, // Center the icon in the middle
                            modifier = Modifier
                                .size(80.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(8.dp) // Şeklin boyutu
                                    .clip(CircleShape)
                                    .background(Color.Red.copy(alpha = 0.2f)),

                                ) {
                                CircularProgressIndicator(
                                    progress = progress, // Bind this to your progress state
                                    modifier = Modifier
                                        .fillMaxSize(), // Adjust padding to control distance between circle and progress indicator
                                    color = Color.Red, // Color of the progress indicator
                                    strokeWidth = 3.dp // Thickness of the progress indicator
                                )
                                Text(
                                    text = "😎", // Your emoji here
                                    modifier = Modifier.align(Alignment.Center),
                                    fontSize = 24.sp // Adjust the size of the emoji
                                )
                            }
                        }
                    }
                    Text(
                        text = title,
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp),
                        fontWeight = FontWeight.Bold

                    )
                    Text(
                        text = "Weekly",
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp),
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        for (i in 1..7) {
                            Box(
                                modifier = Modifier // Sağ üst köşeden biraz boşluk bırakma
                                    .size(15.dp) // Şeklin boyutu
                                    .clip(CircleShape)
                                    .background(Color.Red),
                            ) {
                                Text(
                                    text = weekDays[i - 1],
                                    modifier = Modifier.align(Alignment.Center),
                                    color = Color.White,
                                    fontSize = 8.sp,
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
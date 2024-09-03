package com.example.sun_android

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HabbitCard() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        item {
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clickable {
                    },
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                            .height(275.dp)
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
                                        .padding(8.dp) // Sağ üst köşeden biraz boşluk bırakma
                                )
                                Box(
                                    modifier = Modifier
                                        .size(60.dp) // Şeklin boyutu
                                        .clip(CircleShape)
                                        .background(Color.Red) // Şeklin rengi
                                        .align(Alignment.TopStart) // Sağ üst köşeye yerleştirme
                                        .padding(8.dp) // Sağ üst köşeden biraz boşluk bırakma
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.SwitchCamera,
                                        contentDescription = "none",
                                        Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                            Text(text = "Running", color = Color.White)
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
    HabbitCard()
}
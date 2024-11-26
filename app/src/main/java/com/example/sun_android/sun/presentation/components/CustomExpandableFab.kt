package com.example.sun_android.sun.presentation.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomExpandableFab(
    modifier: Modifier = Modifier,
    items: List<FABItem>,
    onItemClick: (FABItem) -> Unit
) {
    var expanded by remember { mutableStateOf(false) } // Menü durumu için bir state
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 135f else 0f,
        animationSpec = tween(durationMillis = 750)
    )

    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent) // Renksiz yapıldı
    ) {
        // Parent layout
        Column {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    containerColor = Color.Transparent
                ) {
                    items.forEach { item ->

                        DropdownMenuItem(
                            onClick = {
                                onItemClick(item)
                                expanded = false
                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(vertical = 5.dp),
                            text = {
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .border(
                                            1.dp,
                                            Color(0xFF6c6565),
                                            CircleShape
                                        ) // Çerçeve eklendi
                                        .background(Color(0xFF2c292a))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(16.dp), // Padding artırıldı
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(28.dp) // İkon boyutu artırıldı
                                        )
                                        Text(
                                            text = item.text,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        )

                    }
                }


            // Ana buton
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, Color(0xFF6c6565), CircleShape) // Çerçeve eklendi
                    .background(Color(0xFF2c292a))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        expanded = !expanded
                    }
                    .padding(16.dp), // Butonun boyutunu ve iç boşluğunu ayarlıyoruz
                contentAlignment = Alignment.Center // İkonu merkeze hizalıyoruz
            ) {
                Icon(
                    imageVector = Icons.Default.Add, // İstediğiniz bir ikon
                    contentDescription = "Button Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp)
                        .rotate(rotationAngle) // İkona dönme animasyonu ekliyoruz

                )
            }
        }
    }
}

data class FABItem(
    val icon: ImageVector,
    val text: String,
)

@Preview(showBackground = true)
@Composable
fun CustomExpandableFabPreview() {
    CustomExpandableFab(
        items = listOf(
            FABItem(icon = Icons.Rounded.Add, text = "Option 1"),
            FABItem(icon = Icons.Rounded.Add, text = "Option 2"),
            FABItem(icon = Icons.Rounded.Add, text = "Option 3"),
        ),
        onItemClick = { clickedItem ->
            Log.e("Fab button", "Clicked on: ${clickedItem.text}")
        }
    )
}

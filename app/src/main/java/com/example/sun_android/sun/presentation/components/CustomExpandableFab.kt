package com.example.sun_android.sun.presentation.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
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

    var buttonClicked by remember {
        mutableStateOf(false)
    }

    val interactionSource = MutableInteractionSource()

    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent) // Renksiz yapıldı
    ) {

        // parent layout
        Column {

            AnimatedVisibility(
                visible = buttonClicked,
                enter = expandVertically(tween(1500)) + fadeIn(),
                exit = shrinkVertically(tween(1200)) + fadeOut(
                    animationSpec = tween(1000)
                )
            ) {
                // display the items
                Column(
                    modifier = Modifier
                        .padding(vertical = 20.dp, horizontal = 30.dp)
                        .background(Color.Transparent)
                ) {
                    items.forEach { item ->
                        Column(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .border(1.dp, Color(0xFF6c6565), CircleShape) // Çerçeve eklendi
                                    .background(Color(0xFF2c292a))
                                    .clickable(interactionSource = interactionSource,
                                        indication = null, onClick = {
                                            onItemClick(item)
                                            buttonClicked = false
                                        })
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
                                    Text(text = item.text,
                                        color = Color.White
                                    )


                                }
                            }

                            ////////////////////////////////////////////
                        }

                    }
                }
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(1.dp, Color(0xFF6c6565), CircleShape) // Çerçeve eklendi
                    .background(Color(0xFF2c292a))
                    .clickable {
                        buttonClicked = !buttonClicked
                    }
                    .padding(16.dp), // Butonun boyutunu ve iç boşluğunu ayarlıyoruz
                contentAlignment = Alignment.Center // İkonu merkeze hizalıyoruz
            ) {
                Icon(
                    imageVector = Icons.Default.Add, // İstediğiniz bir ikon
                    contentDescription = "Button Icon",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
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

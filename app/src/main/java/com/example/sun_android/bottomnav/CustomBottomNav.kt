package com.example.sun_android.bottomnav


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.SwitchCamera
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
import com.example.sun_android.sun.util.Screens

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomBottomNavigation(
    currentScreenId: String,
    onItemSelected: (Screens) -> Unit
) {
    val items = listOf(
        Screens.HabbitsScreen, Screens.SwipeScreen, Screens.StatisticsScreen
    )


    Row(
        modifier = Modifier
            .background(Color.Black)
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween, // Sol, orta ve sağ grupları ayırmak için
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, Color(0xFF6c6565), CircleShape) // Çerçeve eklendi
                .background(Color(0xFF2c292a))
                .clickable {
                    // Butonun tıklanma olayı
                    // Burada istediğiniz işlevi ekleyebilirsiniz
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

        // Ortadaki iki öğeyi daha yakın yapmak için bir Row içinde gruplama
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp), // Yakınlaştırılmış aralık
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .clip(CircleShape) // Önce clip
                .background(Color(0xFF201e1e)) // Sonra background
                .padding(horizontal = 6.dp, vertical = 8.dp),
        ) {
            CustomBottomNavigationItem(
                item = items[0],
                isSelected = items[0].id == currentScreenId
            ) {
                onItemSelected(items[0])
            }

            CustomBottomNavigationItem(
                item = items[1],
                isSelected = items[1].id == currentScreenId
            ) {
                onItemSelected(items[1])
            }
        }

        // Sağ taraf öğesi
        CustomBottomNavigationItem(item = items[2], isSelected = items[2].id == currentScreenId) {
            onItemSelected(items[2])
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun CustomBottomNavigationItem(item: Screens, isSelected: Boolean, onClick: () -> Unit) {
    val background =
        if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.Transparent
    val contentColor =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .border(1.dp, Color(0xFF6c6565), CircleShape) // Çerçeve eklendi
            .background(Color(0xFF2c292a))
            .clickable(onClick = onClick)
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

            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = item.title,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
@Preview
fun Prev1() {
    CustomBottomNavigation(currentScreenId = Screens.Home.id) {
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun Prev2() {
    CustomBottomNavigationItem(item = Screens.Home, isSelected = true) {
    }
}
package com.example.sun_android.sun.presentation.navigation


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.outlined.ChangeCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sun_android.sun.presentation.components.CustomExpandableFab
import com.example.sun_android.sun.presentation.components.FABItem
import com.example.sun_android.sun.presentation.components.FirstPageContent
import com.example.sun_android.sun.presentation.components.FourthPageContent
import com.example.sun_android.sun.presentation.components.ModularSheetBar
import com.example.sun_android.sun.presentation.components.SecondPageContent
import com.example.sun_android.sun.presentation.components.SixthPageContent
import com.example.sun_android.sun.presentation.components.ThirdPageContent
import com.example.sun_android.sun.presentation.components.todos_components.TodosFirstPageContent
import com.example.sun_android.sun.util.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomNavigation(
    currentScreenId: String,
    onItemSelected: (Screens) -> Unit
) {
    var currentPage by remember { mutableIntStateOf(0) } // İlk sayfa her zaman 0
    var habitName by remember { mutableStateOf("") }
    val totalPages = 5 // Total number of pages
    var additionalInput by remember { mutableStateOf("") }
    val items = listOf(
        Screens.HabbitsScreen, Screens.TodosScreen, Screens.StatisticsScreen
    )
    val showSheet = remember { mutableStateOf(false) }
    val showTodosSheet = remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { 5 })

    LaunchedEffect(showSheet.value) {
        if (showSheet.value) {
            currentPage = 0 // Her seferinde ilk sayfa olsun
            coroutineScope.launch {
                pagerState.scrollToPage(0) // Sayfayı sıfıra taşı
            }
        }
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween, // Sol, orta ve sağ grupları ayırmak için
        verticalAlignment = Alignment.CenterVertically
    ) {

        CustomExpandableFab(
            items = listOf(
                FABItem(icon = Icons.Default.CheckCircleOutline, text = "Add Todo"),
                FABItem(icon = Icons.Outlined.ChangeCircle, text = "Add Habit"),
            ),

            onItemClick = { clickedItem ->
                val items: List<FABItem> = listOf(
                    FABItem(icon = Icons.Default.CheckCircleOutline, text = "Add Todo"),
                    FABItem(icon = Icons.Outlined.ChangeCircle, text = "Add Habit")
                )
                // İlgili item'in indeksini al
                val index = items.indexOf(clickedItem)
                when (index) {
                    0 -> {
                        showTodosSheet.value = !showTodosSheet.value
                        currentPage = 0
                    }

                    1 -> {
                        showSheet.value = !showSheet.value
                        currentPage = 0
                    }

                    else -> Log.e("Fab button", "Unknown item clicked") // Bilinmeyen durum

                }
            }

        )

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
        val progress = currentPage.toFloat() / (totalPages - 1)

        ModularSheetBar(
            showSheet = showSheet.value,
            onDismissRequest = { showSheet.value = false },
            progress = progress,
            onPageChanged = { currentPage = it },
            pagerState = pagerState // PagerState'i burada sağlıyoruz
// Handle page change here
        ) { page, pagerState, coroutineScope ->
            when (page) {
                0 -> FirstPageContent()
                1 -> SecondPageContent()
                2 -> ThirdPageContent()
                3 -> FourthPageContent()
                4 -> SixthPageContent()

            }
        }

        ModularSheetBar(
            showSheet = showTodosSheet.value,
            onDismissRequest = { showTodosSheet.value = false },
            progress = progress,
            onPageChanged = { currentPage = it },
            pagerState = pagerState // PagerState'i burada sağlıyoruz
// Handle page change here
        ) { page, pagerState, coroutineScope ->
            when (page) {
                0 -> TodosFirstPageContent()
                1 -> FirstPageContent()
                2 -> ThirdPageContent()
                3 -> FourthPageContent()
                4 -> SixthPageContent()

            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun CustomBottomNavigationItem(item: Screens, isSelected: Boolean, onClick: () -> Unit) {

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
    CustomBottomNavigation(currentScreenId = Screens.HabbitsScreen.id) {
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun Prev2() {
    CustomBottomNavigationItem(item = Screens.HabbitsScreen, isSelected = true) {
    }
}
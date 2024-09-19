package com.example.sun_android.sun.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.emoji2.emojipicker.EmojiPickerView
import androidx.emoji2.emojipicker.EmojiViewItem
import kotlin.random.Random

@Composable

fun ColorCard() {
    val pagerState = rememberPagerState(initialPage = 3, pageCount = { 7 })
    var selectedEmoji by remember { mutableStateOf("😊") } // Başlangıç emojisi
    var showEmojiPicker by remember { mutableStateOf(false) }

    // Generate 7 random colors and remember them
    val colors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Cyan,
        Color.Magenta,
        Color.Gray
    )

    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(), // Tüm ekranı kaplar
        contentAlignment = Alignment.Center // İçeriği ortalar
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            // Each page content
            Box(
                modifier = Modifier
                    .fillMaxWidth(), // Sayfaları genişlik boyunca hizalar
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = colors[page]),
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                        .graphicsLayer {
                            val pageOffset =
                                (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.1f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }.clickable { showEmojiPicker = true } // Tıklanınca emoji seçiciyi göster
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = selectedEmoji,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }x
                }
            }
        }
        if (showEmojiPicker) {
            val context = LocalContext.current
            AndroidView(
                factory = { ctx ->
                    EmojiPickerView(ctx).apply {
                        setOnEmojiPickedListener { emojiViewItem: EmojiViewItem ->
                            selectedEmoji = emojiViewItem.emoji
                            showEmojiPicker = false // Emoji seçildikten sonra picker'ı kapat
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .align(Alignment.BottomCenter) // Picker'ı ekranın altında göster
            )
        }
    }
}

@Preview
@Composable
fun ColorCardPreview() {
    ColorCard()
}
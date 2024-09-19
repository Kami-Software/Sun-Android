package com.example.sun_android.sun.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.size
import androidx.emoji2.emojipicker.EmojiPickerView
import androidx.emoji2.emojipicker.EmojiViewItem
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun ColorCard() {
    var selectedEmoji by remember { mutableStateOf("😊") } // Başlangıç emojisi
    var showEmojiPicker by remember { mutableStateOf(false) }

    // Renk listesi
    val colors = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Yellow,
        Color.Cyan,
        Color.Magenta,
        Color.Gray
    )

    var emojiScale by remember { mutableFloatStateOf(1f) }
    val animatedScale by animateFloatAsState(
        targetValue = emojiScale,
        animationSpec = tween(durationMillis = 300)
    )
    LaunchedEffect(selectedEmoji) {
        emojiScale = 1.5f // Emojiyi büyüt
        delay(300)        // Bir süre bekle
        emojiScale = 1f   // Emojiyi tekrar normal boyuta getir
    }

    // LazyRow için state oluşturuyoruz
    val listState = rememberLazyListState()

    // Başlangıçta orta karta kaydır
    LaunchedEffect(Unit) {
        listState.scrollToItem(colors.size / 2)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 30.dp) // Kart genişliğinin yarısı
        ) {
            itemsIndexed(colors) { index, color ->
                // Her bir öğe için ofset hesaplaması
                val itemOffset = calculateItemOffset(index, listState)
                val isFocused = itemOffset < 0.1f

                val scale = lerp(0.7f, 1.2f, 1f - itemOffset.coerceIn(0f, 1f))
                val alpha = lerp(0.5f, 1f, 1f - itemOffset.coerceIn(0f, 1f))

                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(containerColor = color),
                    modifier = Modifier
                        .size(100.dp)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        }
                        .clickable(enabled = isFocused) {
                            showEmojiPicker = true
                        }) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (isFocused) {
                            Text(
                                text = selectedEmoji,
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.graphicsLayer(
                                    scaleX = animatedScale,
                                    scaleY = animatedScale
                                )
                            )
                        }
                    }
                }
            }
        }

        // Emoji picker dialogu
        if (showEmojiPicker) {
            AlertDialog(
                containerColor = Color(0xFF201e1e),
                onDismissRequest = { showEmojiPicker = false },
                text = {
                    AndroidView(
                        factory = { ctx ->
                            EmojiPickerView(ctx).apply {
                                setOnEmojiPickedListener { emojiViewItem: EmojiViewItem ->
                                    selectedEmoji = emojiViewItem.emoji
                                    showEmojiPicker = false
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                },
                confirmButton = {}
            )
        }
    }
}

// Ofset hesaplaması için yardımcı fonksiyon
fun calculateItemOffset(index: Int, listState: LazyListState): Float {
    val layoutInfo = listState.layoutInfo
    val visibleItemsInfo = layoutInfo.visibleItemsInfo
    val itemInfo = visibleItemsInfo.find { it.index == index }

    return if (itemInfo != null) {
        val center = layoutInfo.viewportStartOffset.toFloat() +
                (layoutInfo.viewportSize.width.toFloat() / 2f)
        val itemCenter = itemInfo.offset.toFloat() +
                (itemInfo.size.toFloat().toFloat() / 2f)
        val distance = kotlin.math.abs(center - itemCenter)
        val maxDistance = (layoutInfo.viewportSize.width.toFloat() / 2f) +
                (itemInfo.size.toFloat() / 2f)
        (distance / maxDistance).coerceIn(0f, 1f)
    } else {
        1f
    }
}

@Preview
@Composable
fun ColorCardPreview() {
    ColorCard()
}
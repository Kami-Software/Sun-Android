package com.example.sun_android.sun.presentation.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.max

inline val Dp.px: Float
    @Composable get() = with(LocalDensity.current) { this@px.toPx() }

@Stable
data class PickerItemUiModel(
    val title: String,
    @DrawableRes val iconRes: Int? = null,
)

@Composable
fun Picker(
    items: List<PickerItemUiModel>,
    selectedItemIndex: Int = 0,
    onItemSelected: (index: Int) -> Unit,
    pickerDefaults: BottomPickerDefaults = pickerDefaults(),
) {
    val itemHeight = 50.dp
    val visibleItems = 5
    val pickerHeight = itemHeight * visibleItems

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = selectedItemIndex)

    val firstVisibleItemIndexState = remember {
        derivedStateOf { listState.firstVisibleItemIndex }
    }

    val flingBehavior = rememberSnapFlingBehavior(listState)

    val offset by remember {
        derivedStateOf { listState.firstVisibleItemScrollOffset.toFloat() }
    }

    val centerIndex = firstVisibleItemIndexState.value + visibleItems / 2
    // Merkezdeki öğe değiştikçe `onItemSelected` çağrılır
    LaunchedEffect(centerIndex) {
        onItemSelected(centerIndex)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Box(
            modifier = Modifier
                .height(pickerHeight)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(itemHeight),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, pickerDefaults.selectionBoxBorderColor),
                color = Color.Transparent,
                content = {}
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = listState,
                flingBehavior = flingBehavior,
            ) {
                itemsIndexed(items) { index, item ->
                    val distanceFromCenter = (index - centerIndex) - (offset / itemHeight.px)
                    val alphaVal = max(0.2f, 1f - abs(distanceFromCenter) * 0.5f)
                    val angle = distanceFromCenter * 30f

                    Row(
                        modifier = Modifier
                            .height(itemHeight)
                            .fillMaxWidth()
                            .graphicsLayer {
                                scaleY = 1f - alphaVal * 0.2f
                                rotationX = angle * if (index < centerIndex) 1f else -1f
                                alpha = alphaVal
                                transformOrigin = TransformOrigin.Center
                                cameraDistance = itemHeight.toPx() * density * 1000
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        item.iconRes?.let { id ->
                            Icon(
                                painter = painterResource(id = id),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(
                            text = item.title,
                            fontSize = pickerDefaults.itemTitleTextStyle.fontSize,
                            textAlign = TextAlign.Center,
                            color = pickerDefaults.itemTitleTextStyle.color.copy(alpha = alphaVal)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UnitPicker(
    selectedUnits: String,
    onUnitSelected: (unit: String) -> Unit,
) {
    val units = listOf("", "","Km", "Lt", "Cal", "None","", "")

    val items = units.map { title ->
        PickerItemUiModel(title = title)
    }

    Picker(
        items = items,
        selectedItemIndex = units.indexOf(selectedUnits).coerceAtLeast(0),
        onItemSelected = { index ->
            onUnitSelected(units[index])
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BottomPickerPreview() {
    UnitPicker(
        selectedUnits = "Km",
        onUnitSelected = {}
    )
}
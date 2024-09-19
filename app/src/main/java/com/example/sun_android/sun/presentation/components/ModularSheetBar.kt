package com.example.sun_android.sun.presentation.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModularSheetBar(
    showSheet: Boolean,
    onDismissRequest: () -> Unit,
    progress: Float,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onPageChanged: (Int) -> Unit,
    content: @Composable (Int, PagerState, CoroutineScope) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val pagerState = rememberPagerState(pageCount = { 2 })
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 2000) // 2 saniyelik animasyon süresi
    )
    LaunchedEffect(pagerState.currentPage) {
        onPageChanged(pagerState.currentPage)
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp), // Apply 5.dp radius
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            dragHandle = null,
            containerColor = Color(0xFF201e1e) // Sonra background

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = screenHeight * 0.95f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 25.dp, horizontal = 20.dp)
                        .clip(RoundedCornerShape(10.dp)) // Köşeleri yuvarla
                        .background(Color(0xFFec3557)) // Arka plan rengi
                ) {
                    LinearProgressIndicator(
                        progress = animatedProgress, // Animasyonlu ilerleme değeri
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .clip(RoundedCornerShape(10.dp)), // Köşeleri yuvarla
                        color = Color.Transparent,
                        trackColor = Color(0xFF7d2f40),
                        strokeCap = StrokeCap.Butt
                    )
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f) // Pager'ın fazla yer kaplamasını önle
                ) { page ->
                    content(page, pagerState, coroutineScope)
                }

                Button(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 40.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        coroutineScope.launch {
                            val nextPage = pagerState.currentPage + 1
                            if (nextPage < pagerState.pageCount) {
                                pagerState.animateScrollToPage(nextPage)
                            }
                        }
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors( Color(0xFFec3557), Color.White)
                ) {
                    Text(text = "Next")
                }

            }
        }
    }
}

@Preview
@Composable
fun SheetBarPreview() {
    Button(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 40.dp)
            .fillMaxWidth()
            .height(50.dp),
        onClick = {},
        shape = CircleShape,
        colors = ButtonColors( Color(0xFFec3557), Color.White, Color(0xFFec3557), Color(0xFFec3557)),
    ) {
        Text(text = "Next")
    }


}
package com.example.sun_android.sun.presentation.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
    pagerState: PagerState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onPageChanged: (Int) -> Unit,
    content: @Composable (Int, PagerState, CoroutineScope) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
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
            containerColor = Color(0xFF201e1e)
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
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFec3557))
                ) {
                    LinearProgressIndicator(
                        progress = {animatedProgress},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        color = Color.Transparent,
                        trackColor = Color(0xFF7d2f40),
                        strokeCap = StrokeCap.Butt
                    )
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { page ->
                    content(page, pagerState, coroutineScope)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 40.dp)
                ) {
                    // IconButton sadece 1. sayfada gizlenecek, diğer sayfalarda görünür olacak
                    AnimatedVisibility(
                        visible = pagerState.currentPage > 0,
                        enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
                        exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start)
                    ) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            },
                            modifier = Modifier.size(50.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                contentColor = Color(0xFFec3557),
                                containerColor = Color(0xFFec3557)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back Button",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                        }
                    }

                    // Button'un padding ve görünürlüğü animasyonlu olarak ayarlanacak
                    AnimatedVisibility(
                        visible = true, // Buton her zaman görünür, ama animasyonla yer değiştirir
                        enter = fadeIn() + expandHorizontally(expandFrom = Alignment.End),
                        exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.End)
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = if (pagerState.currentPage > 0) 20.dp else 0.dp)
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
                            colors = ButtonDefaults.buttonColors(Color(0xFFec3557), Color.White)
                        ) {
                            Text(text = if (pagerState.currentPage == pagerState.pageCount - 1) "Save" else "Next")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SheetBarPreview() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 40.dp),
    ) {
        IconButton(
            onClick = {
            },
            modifier = Modifier.size(50.dp),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color(0xFFec3557),
                containerColor = Color(0xFFec3557)
            )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Icon Button",
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .height(50.dp),
            onClick = {},
            shape = CircleShape,
            colors = ButtonColors(
                Color(0xFFec3557),
                Color.White,
                Color(0xFFec3557),
                Color(0xFFec3557)
            ),
        ) {
            Text(text = "Next")
        }
    }
}

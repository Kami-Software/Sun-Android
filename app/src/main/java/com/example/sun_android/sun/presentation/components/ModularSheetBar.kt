package com.example.sun_android.sun.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModularSheetBar(
    showSheet: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            content()
        }
    }
}
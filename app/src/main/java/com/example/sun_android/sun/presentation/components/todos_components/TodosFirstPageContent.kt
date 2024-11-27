package com.example.sun_android.sun.presentation.components.todos_components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodosFirstPageContent(
) {
    var todoName by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(top = 15.dp),
            text = "Give your todo a name",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )

        // Subtitle Text
        Text(
            text = "Choose a clear and meaningful name for your todo",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFB0B0B0), // Lighter gray for subtitle
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )


        OutlinedTextField(
            value = todoName,
            onValueChange = { todoName = it },
            placeholder = { Text(text = "Todo name", fontSize = 18.sp) },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White, // Light gray for the text
                textAlign = TextAlign.Start,
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Black,
                focusedTextColor = Color.White,
                focusedContainerColor = Color.Black,
                cursorColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )


    }
}

@Composable
@Preview
fun FirstPageContentPreview() {
    TodosFirstPageContent(
    )
}
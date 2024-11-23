package com.example.sun_android.sun.presentation.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults


@Composable
fun FourthPageContent() {
    var selectedUnits by remember { mutableStateOf("Km") }
    var number by remember { mutableStateOf("") }
    var timesText by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.Tag,
            contentDescription = "Icon Button",
            modifier = Modifier.size(100.dp),
            tint = Color(0xFFec3557)
        )

        Text(
            modifier = Modifier.padding(top = 15.dp),
            text = "Daily Goal",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Set a realistic for your habit",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFB0B0B0), // Lighter gray for subtitle
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth(0.90F) // Adjust the width as needed
                .height(80.dp) // Adjust height
                .clip(RoundedCornerShape(10.dp)), // Rounded corners
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2D2D31),
                unfocusedLabelColor = Color.White,
                unfocusedIndicatorColor = Color(0xFF2D2D31),
                focusedIndicatorColor = Color(0xFF2D2D31),
                focusedContainerColor = Color(0xFF2D2D31),
                disabledContainerColor = Color(0xFF2D2D31),
                cursorColor = Color(0xFF0091FF)
            ),
            placeholder = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center // Center placeholder
                ) {
                    Text(
                        text = "1",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        )

        Row(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically // Align items vertically in the center

        ) {
            Text(
                text = "Custom Unit",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White, // Lighter gray for subtitle
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFFec3557),
                )
            )
        }
        if (!checked){
            UnitPicker(
                selectedUnits = selectedUnits,
                onUnitSelected = { unit -> Log.e("Unit:","Selected Unit: $unit") }
            )
        }
        else{
            OutlinedTextField(
                value = timesText,
                onValueChange = { timesText = it },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth(0.90F) // Adjust the width as needed
                    .height(80.dp) // Adjust height
                    .clip(RoundedCornerShape(10.dp)), // Rounded corners
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFF2D2D31),
                    unfocusedLabelColor = Color.White,
                    unfocusedIndicatorColor = Color(0xFF2D2D31),
                    focusedIndicatorColor = Color(0xFF2D2D31),
                    focusedContainerColor = Color(0xFF2D2D31),
                    disabledContainerColor = Color(0xFF2D2D31),
                    cursorColor = Color(0xFF0091FF)
                ),
                placeholder = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center // Center placeholder
                    ) {
                        Text(
                            text = "Times",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            )
        }



    }
}

@Composable
@Preview
fun FourthPageContentView() {
    FourthPageContent()
}
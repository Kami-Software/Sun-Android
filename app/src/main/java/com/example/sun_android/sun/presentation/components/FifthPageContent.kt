package com.example.sun_android.sun.presentation.components

import android.icu.text.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material.icons.outlined.EventRepeat
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FifthPageContent() {
    val state = rememberDateRangePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.EventRepeat,
            contentDescription = "Icon Button",
            modifier = Modifier.size(100.dp),
            tint = Color(0xFFec3557)
        )

        Text(
            modifier = Modifier.padding(top = 15.dp),
            text = "Start Date",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Choose the start date of this habit",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFB0B0B0), // Lighter gray for subtitle
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        DateRangePicker(state,
            title = {null},
            modifier = Modifier,
            showModeToggle = false,
            colors = DatePickerDefaults.colors(
                containerColor = Color.Transparent,
                headlineContentColor = Color.Transparent,
                weekdayContentColor = Color.Gray,
                subheadContentColor = Color.White,
                yearContentColor = Color.Green,
                currentYearContentColor = Color.Red,
                selectedYearContainerColor = Color.Red,
                disabledDayContentColor = Color.Gray,
                todayDateBorderColor = Color(0xFFec3557),
                dayInSelectionRangeContainerColor =  Color.Transparent,//Color(0xFFB0B0B0)
                dayInSelectionRangeContentColor = Color(0xFFec3557),
                todayContentColor =Color.White ,
                disabledSelectedDayContentColor = Color.Gray,
                navigationContentColor = Color.Yellow,
                selectedDayContainerColor = Color(0xFFec3557),
                dayContentColor = Color.White,
                disabledYearContentColor = Color.Gray,
                dividerColor = Color.Black,
                disabledSelectedDayContainerColor = Color.Gray,
                selectedDayContentColor = Color.White,
            )
        )
    }
}

@Composable
@Preview
fun FifthPageContentView() {
    FifthPageContent()
}
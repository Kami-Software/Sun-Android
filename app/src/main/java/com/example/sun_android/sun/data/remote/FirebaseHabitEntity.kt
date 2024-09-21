package com.example.sun_android.sun.data.remote

import com.google.firebase.firestore.PropertyName

class FirebaseHabitEntity (
    @PropertyName("color") val color: String = "yellow",
    @PropertyName("completed") val completed: Boolean = false,
    @PropertyName("currentCount") val currentCount: Int = 0,
    @PropertyName("goalCount") val goalCount: Int = 0,
    @PropertyName("icon") val icon: String = "😀",
    @PropertyName("id") val id: String = "",
    @PropertyName("selectedDate") val selectedDate: Map<String, Boolean> = mapOf(
        "Friday" to false,
        "Monday" to true,
        "Saturday" to false,
        "Sunday" to true,
        "Thursday" to true,
        "Tuesday" to false,
        "Wednesday" to true
    ),
    @PropertyName("title") val title: String = "",
    @PropertyName("type") val type: String = "Weekly"

) {
}
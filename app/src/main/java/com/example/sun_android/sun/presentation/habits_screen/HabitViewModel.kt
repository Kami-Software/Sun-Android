package com.example.sun_android.sun.presentation.habits_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sun_android.sun.data.remote.FirebaseHabitEntity
import com.example.sun_android.sun.domain.repository.FirebaseHabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitViewModel @Inject constructor(
    private val repository: FirebaseHabitRepository
) : ViewModel() {

    private val _habits = MutableStateFlow<List<FirebaseHabitEntity>>(emptyList())
    val habits: StateFlow<List<FirebaseHabitEntity>> get() = _habits

    fun loadAllHabits() {
        viewModelScope.launch {
            repository.getAllHabits().collect { habitList ->
                _habits.value = habitList
            }
        }
    }
}
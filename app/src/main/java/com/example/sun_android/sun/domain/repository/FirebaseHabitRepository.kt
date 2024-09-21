package com.example.sun_android.sun.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.sun_android.sun.data.remote.FirebaseHabitEntity

interface FirebaseHabitRepository {
    suspend fun addHabit(habit: FirebaseHabitEntity)
    suspend fun updateHabit(habit: FirebaseHabitEntity)
    suspend fun deleteHabit(id: String)
    suspend fun getHabitById(id: String): Flow<FirebaseHabitEntity?>
    suspend fun getAllHabits(): Flow<List<FirebaseHabitEntity>>
}
package com.example.sun_android.sun.data.repository

import android.util.Log
import com.example.sun_android.sun.data.remote.FirebaseHabitEntity
import com.example.sun_android.sun.domain.repository.FirebaseHabitRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseHabitRepositoryImpl(
    private val firestore: FirebaseFirestore
): FirebaseHabitRepository {
    private val habitsCollection = firestore.collection("habits").document("userId123").collection("swimming")

    override suspend fun addHabit(habit: FirebaseHabitEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateHabit(habit: FirebaseHabitEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteHabit(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getHabitById(id: String): Flow<FirebaseHabitEntity?> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllHabits(): Flow<List<FirebaseHabitEntity>> = callbackFlow {
        val listenerRegistration: ListenerRegistration = habitsCollection
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("FirebaseHabitRepository", "Error getting habits", error)
                    close(error) // Close the flow on error
                } else {
                    Log.e("FirebaseHabitRepository", "Error getting habits or snapshot is null", error)
                    val habits = snapshot?.toObjects(FirebaseHabitEntity::class.java) ?: emptyList()
                    trySend(habits).isSuccess // Send the data to the flow
                }
            }

        // Cancel the listener when the flow is closed
        awaitClose { listenerRegistration.remove() }
    }

}
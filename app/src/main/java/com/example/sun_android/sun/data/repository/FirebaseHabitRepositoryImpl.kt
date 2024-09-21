package com.example.sun_android.sun.data.repository

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
    private val habitsCollection = firestore.collection("habits")

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
                    close(error) // Close the flow on error
                } else {
                    val habits = snapshot?.toObjects(FirebaseHabitEntity::class.java) ?: emptyList()
                    trySend(habits).isSuccess // Send the data to the flow
                }
            }

        // Cancel the listener when the flow is closed
        awaitClose { listenerRegistration.remove() }
    }

}
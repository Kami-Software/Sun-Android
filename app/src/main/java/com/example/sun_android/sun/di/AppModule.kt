package com.example.sun_android.sun.di

import com.example.sun_android.sun.data.repository.FirebaseHabitRepositoryImpl
import com.example.sun_android.sun.domain.repository.FirebaseHabitRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesFirebaseRepositoryImpl(firestore: FirebaseFirestore): FirebaseHabitRepository {
        return FirebaseHabitRepositoryImpl(firestore)
    }

    @Provides
    @Singleton
    fun providesFirebaseFirestore() = FirebaseFirestore.getInstance()
}
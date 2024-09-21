package com.example.sun_android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SunApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
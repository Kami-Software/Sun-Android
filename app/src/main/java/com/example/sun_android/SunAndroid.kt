package com.example.sun_android

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SunAndroid : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
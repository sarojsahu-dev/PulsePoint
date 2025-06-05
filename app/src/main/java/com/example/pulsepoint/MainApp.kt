package com.example.pulsepoint

import android.app.Application
import com.example.pulsepoint.networking.Networking
import com.example.pulsepoint.networking.OkHttpImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Networking.init(baseUrl = "https://api.data.gov.in/", okHttpSetup = OkHttpImpl())
    }
}
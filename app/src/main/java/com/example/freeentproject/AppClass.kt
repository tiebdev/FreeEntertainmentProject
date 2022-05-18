package com.example.freeentproject
import android.app.Application
import android.os.SystemClock
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass: Application() {
    override fun onCreate() {
        super.onCreate()
        SystemClock.sleep(4000)
    }
}
package com.example.garbanzo

import android.app.Application
import com.example.garbanzo.di.appModule
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appModule)
        }
    }
}
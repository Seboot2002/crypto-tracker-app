package com.sebascamayo.cryptotracker

import android.app.Application
import com.sebascamayo.cryptotracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CryptoTrackerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoTrackerApp)
            androidLogger()

            modules(modules = appModule)
        }
    }
}
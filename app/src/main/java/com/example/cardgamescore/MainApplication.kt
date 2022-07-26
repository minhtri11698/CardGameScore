package com.example.cardgamescore

import android.app.Application
import com.example.cardgamescore.di.dataModule
import com.example.cardgamescore.di.localDataModule
import com.example.cardgamescore.di.useCaseModule
import com.example.cardgamescore.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApplication)
            modules(localDataModule, dataModule, useCaseModule, viewModelModule)
        }
    }
    companion object {
        lateinit var instance: MainApplication
    }
}
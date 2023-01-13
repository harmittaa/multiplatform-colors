package com.harmittaa.multiplatformcolors.android

import android.app.Application
import appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MultiplatformColorsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MultiplatformColorsApplication)
            androidLogger()
            modules(appModule())
        }
    }
}

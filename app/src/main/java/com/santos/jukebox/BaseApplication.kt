package com.santos.jukebox

import android.app.Application
import com.santos.jukebox.establishment.di.modulesClient
import com.santos.jukebox.establishment.di.modulesEstablishment
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(modulesEstablishment, modulesClient)
        }
    }
}
package com.santos.jukebox

import androidx.multidex.MultiDexApplication
import com.santos.jukebox.base.di.baseModule
import com.santos.jukebox.client.di.modulesClient
import com.santos.jukebox.establishment.di.modulesEstablishment
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(baseModule, modulesEstablishment, modulesClient)
        }
    }
}
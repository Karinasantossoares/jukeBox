package com.santos.jukebox.base.di

import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val baseModule = module {
    single { FirebaseDatabase.getInstance().reference }
}

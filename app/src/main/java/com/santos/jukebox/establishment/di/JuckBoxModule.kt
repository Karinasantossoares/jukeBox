package com.santos.jukebox.establishment.di

import com.google.firebase.database.FirebaseDatabase
import com.santos.jukebox.establishment.useCase.MusicUseCase
import com.santos.jukebox.establishment.viewmodel.RegisterMusicViewModel
import com.santos.jukebox.establishment.remote.FirebaseMusic
import com.santos.jukebox.establishment.remote.FirebaseTypeMusic
import com.santos.jukebox.establishment.repository.MusicRepository
import com.santos.jukebox.establishment.repository.TypeMusicRepository
import com.santos.jukebox.establishment.useCase.RegisterTypeMusicUseCase
import com.santos.jukebox.establishment.viewmodel.RegisterTypeMusicViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesEstablishment = module {
    single { FirebaseDatabase.getInstance().reference }
    single { FirebaseMusic(get()) }
    single { FirebaseTypeMusic(get()) }
    single { MusicRepository(get()) }
    single { TypeMusicRepository(get()) }
    single { MusicUseCase(get(), get()) }
    single { RegisterTypeMusicUseCase(androidContext(), get()) }
    viewModel { RegisterMusicViewModel(get(),get(), androidContext() ) }
    viewModel { RegisterTypeMusicViewModel(get() ) }
}
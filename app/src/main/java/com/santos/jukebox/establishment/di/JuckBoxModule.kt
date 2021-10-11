package com.santos.jukebox.establishment.di

import com.google.firebase.database.FirebaseDatabase
import com.santos.jukebox.client.remote.FirebaseClient
import com.santos.jukebox.client.repository.ClientRepository
import com.santos.jukebox.client.usecase.ClientUseCase
import com.santos.jukebox.client.viewmodel.ClientViewModel
import com.santos.jukebox.establishment.remote.FirebaseMusic
import com.santos.jukebox.establishment.remote.FirebaseQueueMusic
import com.santos.jukebox.establishment.remote.FirebaseTypeMusic
import com.santos.jukebox.establishment.repository.MusicRepository
import com.santos.jukebox.establishment.repository.TypeMusicRepository
import com.santos.jukebox.establishment.useCase.MusicQueueUseCase
import com.santos.jukebox.establishment.useCase.MusicUseCase
import com.santos.jukebox.establishment.useCase.RegisterTypeMusicUseCase
import com.santos.jukebox.establishment.viewmodel.ManagerMusicViewModel
import com.santos.jukebox.establishment.viewmodel.QueueMusicViewModel
import com.santos.jukebox.establishment.viewmodel.RegisterMusicViewModel
import com.santos.jukebox.establishment.viewmodel.RegisterTypeMusicViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesEstablishment = module {
    single { FirebaseDatabase.getInstance().reference }
    single { FirebaseMusic(get()) }
    single { FirebaseQueueMusic(get()) }
    single { FirebaseTypeMusic(get()) }
    single { MusicRepository(get(), get()) }
    single { TypeMusicRepository(get()) }
    single { MusicUseCase(get(), get()) }
    single { MusicQueueUseCase(get()) }
    single { RegisterTypeMusicUseCase(androidContext(), get()) }
    viewModel { RegisterMusicViewModel(get(), get(), androidContext()) }
    viewModel { ManagerMusicViewModel(get()) }
    viewModel { RegisterTypeMusicViewModel(get() ) }
    viewModel { QueueMusicViewModel(get() ) }
}

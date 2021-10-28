package com.santos.jukebox.establishment.di

import com.santos.jukebox.client.remote.HistoryFirebase
import com.santos.jukebox.client.repository.HistoryRepository
import com.santos.jukebox.establishment.remote.FirebaseMusic
import com.santos.jukebox.establishment.remote.FirebaseQueueMusic
import com.santos.jukebox.establishment.remote.FirebaseTypeMusic
import com.santos.jukebox.establishment.repository.MusicRepository
import com.santos.jukebox.establishment.repository.TypeMusicRepository
import com.santos.jukebox.establishment.useCase.GraphUseCase
import com.santos.jukebox.establishment.useCase.MusicQueueUseCase
import com.santos.jukebox.establishment.useCase.MusicUseCase
import com.santos.jukebox.establishment.useCase.RegisterTypeMusicUseCase
import com.santos.jukebox.establishment.viewmodel.*
import com.santos.jukebox.establishment.viewmodel.GraphViewModel
import com.santos.jukebox.establishment.viewmodel.ManagerMusicViewModel
import com.santos.jukebox.establishment.viewmodel.QueueMusicViewModel
import com.santos.jukebox.establishment.viewmodel.RegisterMusicViewModel
import com.santos.jukebox.establishment.viewmodel.RegisterTypeMusicViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesEstablishment = module {
    single { FirebaseMusic(get()) }
    single { HistoryFirebase(get()) }
    single { FirebaseQueueMusic(get()) }
    single { FirebaseTypeMusic(get()) }
    single { MusicRepository(get(), get()) }
    single { HistoryRepository(get()) }
    single { TypeMusicRepository(get()) }
    single { MusicUseCase(get(), get()) }
    single { MusicQueueUseCase(get()) }
    single { GraphUseCase(get()) }
    single { RegisterTypeMusicUseCase(get(), get()) }
    viewModel { RegisterMusicViewModel(get(), get(), get(), get()) }
    viewModel { ManagerMusicViewModel(get()) }
    viewModel { RegisterTypeMusicViewModel(get()) }
    viewModel { QueueMusicViewModel(get()) }
    viewModel { GraphViewModel(get()) }
}

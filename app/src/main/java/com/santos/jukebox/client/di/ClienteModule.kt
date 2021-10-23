package com.santos.jukebox.client.di

import com.google.firebase.database.FirebaseDatabase
import com.santos.jukebox.client.remote.FirebaseClient
import com.santos.jukebox.client.repository.ClientRepository
import com.santos.jukebox.client.usecase.ClientUseCase
import com.santos.jukebox.client.viewmodel.ClientViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesClient = module {
    single { FirebaseClient(get()) }
    single { ClientRepository(get()) }
    single { ClientUseCase(get(), get()) }
    viewModel { ClientViewModel(get()) }
}
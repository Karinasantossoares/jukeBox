package com.santos.jukebox.establishment.di

import com.google.firebase.database.FirebaseDatabase
import com.santos.establishment.useCase.RegisterEstablishmentUseCase
import com.santos.establishment.viewmodel.RegisterEstablishmentViewModel
import com.santos.jukebox.establishment.persistence.FirebaseEstablishment
import com.santos.jukebox.establishment.repository.EstablishmentRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesEstablishment = module {
    single { EstablishmentRepository(get()) }
    single { RegisterEstablishmentUseCase(get()) }
    single { FirebaseEstablishment(get()) }
    single { FirebaseDatabase.getInstance().reference }
    viewModel { RegisterEstablishmentViewModel(get(), androidContext()) }
}
package com.santos.jukebox.di

import com.google.firebase.database.FirebaseDatabase
import com.santos.establishment.repository.EstablishmentRepository
import com.santos.establishment.useCase.EstablishmentUseCase
import com.santos.establishment.viewmodel.EstablishmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesEstablishment = module {
    single { EstablishmentRepository(get()) }
    single { EstablishmentUseCase(get()) }
    single { FirebaseDatabase.getInstance().reference }
    viewModel { EstablishmentViewModel(get(), get()) }
}
package com.santos.jukebox.client.di

import com.santos.jukebox.establishment.repository.RecommendedRepository
import com.santos.jukebox.establishment.useCase.RecommendedUseCase
import com.santos.jukebox.establishment.viewmodel.RecommendedMusicViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleRecommended = module {
    single { RecommendedRepository(get()) }
    single { RecommendedUseCase(get(), get()) }
    viewModel { RecommendedMusicViewModel(get()) }
}
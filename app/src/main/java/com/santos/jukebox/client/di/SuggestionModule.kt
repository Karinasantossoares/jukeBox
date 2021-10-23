package com.santos.jukebox.client.di

import com.santos.jukebox.client.remote.SuggestionFirebase
import com.santos.jukebox.client.repository.SuggestionRepository
import com.santos.jukebox.client.usecase.SuggestionUseCase
import com.santos.jukebox.client.viewmodel.SuggestionViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleSuggestion = module {
    single { SuggestionFirebase(get()) }
    single { SuggestionRepository(get()) }
    single { SuggestionUseCase(get(), get()) }
    viewModel { SuggestionViewModel(get()) }
}

package com.santos.jukebox.client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.client.data.SuggestionResponse
import com.santos.jukebox.client.ui.state.StateSuggestion
import com.santos.jukebox.client.usecase.SuggestionUseCase

class SuggestionViewModel(
    private val useCase: SuggestionUseCase
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateSuggestion>()
    val stateLiveData: LiveData<StateSuggestion>
        get() = _stateLiveData

    private fun notifyLiveData(liveData: StateSuggestion) {
        _stateLiveData.value = liveData
    }

    init {
        getAllSuggestionMusic()
    }

    private fun getAllSuggestionMusic() {
        notifyLiveData(StateSuggestion.Loading)
        useCase.getAllSuggestionMusic(
            success = {
                _stateLiveData.postValue(
                    StateSuggestion.SuccessListMusic(it)
                )
            },
            error = {
                _stateLiveData.postValue(
                    StateSuggestion.ShowMessage(it.localizedMessage)
                )
            }
        )
    }

    fun addSuggestionMusic(suggestion: SuggestionResponse) {
        useCase.addSuggestionMusic(
            success = {
                _stateLiveData.postValue(
                    StateSuggestion.ShowMessage(it)
                )
            },
            error = {
                _stateLiveData.postValue(
                    StateSuggestion.ShowMessage(it.localizedMessage)
                )
            },
            suggestionResponse = suggestion
        )
    }
}
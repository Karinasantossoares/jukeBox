package com.santos.jukebox.establishment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.client.ui.state.StateRecommended
import com.santos.jukebox.establishment.useCase.RecommendedUseCase


class RecommendedMusicViewModel(
    private val useCase: RecommendedUseCase
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateRecommended>()
    val stateLiveData: LiveData<StateRecommended>
        get() = _stateLiveData

    private fun notifyLiveData(liveData: StateRecommended) {
        _stateLiveData.value = liveData
    }

    init {
        getAllSuggestionMusic()
    }

    private fun getAllSuggestionMusic() {
        notifyLiveData(StateRecommended.Loading)
        useCase.getAllSuggestionMusic(
            success = {
                notifyLiveData(
                    StateRecommended.SuccessListMusic(it)
                )
            },
            error = {
                notifyLiveData(
                    StateRecommended.ShowMessage(it.localizedMessage)
                )
            }
        )
    }

    fun deleteRecommendedMusic(
        id: String
    ) {
        notifyLiveData(StateRecommended.Loading)
        useCase.deleteRecommendedMusic(
            success = {
                notifyLiveData(
                    StateRecommended.ShowMessage(it)
                )
            },
            error = {
                notifyLiveData(
                    StateRecommended.ShowMessage(it.localizedMessage)
                )
            },
            idMusic = id

        )
    }
}
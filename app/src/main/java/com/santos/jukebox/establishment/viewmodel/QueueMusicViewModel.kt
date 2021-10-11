package com.santos.jukebox.establishment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.ui.state.EventListMusic
import com.santos.jukebox.establishment.ui.state.StateListMusic
import com.santos.jukebox.establishment.ui.state.StateQueueMusic
import com.santos.jukebox.establishment.useCase.MusicQueueUseCase
import com.santos.jukebox.establishment.useCase.MusicUseCase

internal class QueueMusicViewModel(
    private val useCaseMusic: MusicQueueUseCase
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateQueueMusic>()
    val stateLiveData: LiveData<StateQueueMusic>
        get() = _stateLiveData

    init {
        useCaseMusic.getAllQueueMusics(
            success = {
                if (it.isEmpty()) {
                    _stateLiveData.value = StateQueueMusic.EmptyList
                } else {
                    _stateLiveData.value = StateQueueMusic.SuccessListMusic(it)
                }
            },
            error = {
                it.localizedMessage?.let { errorMessage ->
                    _stateLiveData.value = StateQueueMusic.ShowMessage(errorMessage)
                }
            }
        )
    }

    fun markAsFinish(id: String?) {
        id?.let {
            useCaseMusic.markAsFinish(
                id = id,
                success = {},
                error = {
                    it.localizedMessage?.let { errorMessage ->
                        _stateLiveData.value = StateQueueMusic.ShowMessage(errorMessage)
                    }
                }
            )
        }
    }

}
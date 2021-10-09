package com.santos.jukebox.client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.ui.state.StateClient
import com.santos.jukebox.client.usecase.ClientUseCase

class ClientViewModel(private val useCase: ClientUseCase) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateClient>()
    val stateLiveData: LiveData<StateClient>
        get() = _stateLiveData

    private var _actionLiveData = MutableLiveData<StateClient>()
    val actionLiveData: LiveData<StateClient>
        get() = _actionLiveData

    private fun notifyLiveData(liveData: StateClient) {
        _stateLiveData.value = liveData
    }

    init {
        getVisibleMusics()
    }

    private fun getVisibleMusics() {
        notifyLiveData(StateClient.Loading)
        useCase.getVisibleMusic(
            success = {
                notifyLiveData(StateClient.SuccessListMusic(it))
            },
            error = {
                notifyLiveData(StateClient.ShowMessage(it.localizedMessage))
            }
        )
    }

    fun addMusicQueue(
        isChecked: Boolean,
        music: Music,
    ) {
        notifyLiveData(StateClient.Loading)
        useCase.addMusicQueue(
            isChecked = isChecked,
            music = music,
            success = {
                notifyLiveData(StateClient.ShowMessage(it))
            },
            error = {
                notifyLiveData(StateClient.ShowMessage(it.localizedMessage))
            }
        )
    }
}
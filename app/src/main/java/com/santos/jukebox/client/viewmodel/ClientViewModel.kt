package com.santos.jukebox.client.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.data.MusicResponse
import com.santos.jukebox.client.ui.state.StateClient
import com.santos.jukebox.client.usecase.ClientUseCase

class ClientViewModel(private val useCase: ClientUseCase) : ViewModel() {

    private var currentTypes: List<MusicResponse>? = null
    private var _stateLiveData = MutableLiveData<StateClient>()
    val stateLiveData: LiveData<StateClient>
        get() = _stateLiveData

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
                this.currentTypes = it
                showListClient(it)
            },
            error = {
                it.localizedMessage?.let { error ->
                    notifyLiveData(StateClient.ShowMessage(error))
                }
            }
        )
    }

    fun filterResults(text: String) {
        currentTypes?.map {
            return@map MusicResponse(
                type = it.type,
                musics = it.musics.filter { music ->
                    music.title.lowercase().contains(text.lowercase())
                }.toMutableList()
            )
        }?.filter { it.musics.isNotEmpty() }?.let { listFiltered ->
            showListClient(listFiltered)
        }

    }

    private fun showListClient(list: List<MusicResponse>) {
        if (list.isEmpty()) {
            notifyLiveData(StateClient.SuccessEmptyList)
        } else {
            notifyLiveData(StateClient.SuccessListMusic(list))
        }
    }

    fun addMusicQueue(
        selectedMusic: Boolean,
        music: Music,
    ) {
        notifyLiveData(StateClient.Loading)
        useCase.addMusicQueue(
            isChecked = selectedMusic,
            music = music,
            success = {
                notifyLiveData(StateClient.SuccessRequestedMusic)
                notifyLiveData(StateClient.ShowMessage(it))
                useCase.addMusicHistory(music)
            },
            error = {
                it.localizedMessage?.let { error ->
                    notifyLiveData(StateClient.ShowMessage(error))
                }
            }
        )
    }
}
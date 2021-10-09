package com.santos.jukebox.establishment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.ui.state.EventListMusic
import com.santos.jukebox.establishment.ui.state.StateListMusic
import com.santos.jukebox.establishment.useCase.MusicUseCase

internal class ManagerMusicViewModel(
    private val useCaseMusic: MusicUseCase
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateListMusic>()
    val stateLiveData: LiveData<StateListMusic>
        get() = _stateLiveData

    private var _actionLiveData = MutableLiveData<EventListMusic>()
    val actionLiveData: LiveData<EventListMusic>
        get() = _actionLiveData

    init {
        findAll()
    }

    private fun findAll() {
        _stateLiveData.value = StateListMusic.Loading
        useCaseMusic.findAll(
            success = {
                _stateLiveData.value = StateListMusic.SuccessListMusic(it)
            },
            error = {
                it.localizedMessage?.let { error ->
                    _stateLiveData.value = StateListMusic.ShowMessage(error)
                }
            }
        )
    }

    fun tapOnEdit(music: RegisterMusicEstablishment) {

    }

    fun tapOnDelete(music: RegisterMusicEstablishment) {

    }

}
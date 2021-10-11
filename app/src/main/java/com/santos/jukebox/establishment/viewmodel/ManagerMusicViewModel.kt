package com.santos.jukebox.establishment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.R
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.ui.state.EventListMusic
import com.santos.jukebox.establishment.ui.state.StateListMusic
import com.santos.jukebox.establishment.useCase.MusicUseCase

internal class ManagerMusicViewModel(
    private val useCaseMusic: MusicUseCase,
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateListMusic>()
    val stateLiveData: LiveData<StateListMusic>
        get() = _stateLiveData

    private var _actionLiveData = SingleLiveEvent<EventListMusic>()
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

    fun deleteMusic(music: RegisterMusicEstablishment) {
        music.id?.let { it ->
            useCaseMusic.deleteMusic(
                it,
                success = {
                    _stateLiveData.value = StateListMusic.ShowMessageId(R.string.success_delete_music)
                },
                error = {
                    it.localizedMessage?.let { error ->
                        _stateLiveData.value = StateListMusic.ShowMessage(error)
                    }
                }
            )
        }
    }

    fun tapOnEdit(music: RegisterMusicEstablishment) {
        _actionLiveData.value = EventListMusic.EditMusic(music)
    }

    fun tapOnDelete(music: RegisterMusicEstablishment) {
        _actionLiveData.value = EventListMusic.DeleteMusic(music)
    }

}
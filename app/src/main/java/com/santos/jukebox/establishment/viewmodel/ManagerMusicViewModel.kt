package com.santos.jukebox.establishment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.R
import com.santos.jukebox.establishment.data.MusicEstablishmentResponse
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

    private var currentTypes: List<MusicEstablishmentResponse>? = null

    init {
        findAll()
    }

    private fun findAll() {
        _stateLiveData.value = StateListMusic.Loading
        useCaseMusic.findAll(
            success = {
                this.currentTypes = it
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
                    _stateLiveData.value =
                        StateListMusic.ShowMessageId(R.string.success_delete_music)
                },
                error = {
                    it.localizedMessage?.let { error ->
                        _stateLiveData.value = StateListMusic.ShowMessage(error)
                    }
                }
            )
        }
    }

    fun filterResultsSearchView(text: String) {
        currentTypes?.map {
            return@map MusicEstablishmentResponse(
                it.type,
                it.musics.filter { music ->
                    music.title.lowercase().contains(text.lowercase())
                }.toMutableList()
            )
        }?.filter { it.musics.isNotEmpty() }?.let {
            _stateLiveData.value =
                StateListMusic.SuccessListMusic(it)
        }
    }

    fun tapOnEdit(music: RegisterMusicEstablishment) {
        _actionLiveData.value = EventListMusic.EditMusic(music)
    }

    fun tapOnDelete(music: RegisterMusicEstablishment) {
        _actionLiveData.value = EventListMusic.DeleteMusic(music)
    }

}
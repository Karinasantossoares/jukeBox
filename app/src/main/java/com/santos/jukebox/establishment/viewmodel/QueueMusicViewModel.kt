package com.santos.jukebox.establishment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.ui.state.EventListMusic
import com.santos.jukebox.establishment.ui.state.StateListMusic
import com.santos.jukebox.establishment.useCase.MusicUseCase

internal class QueueMusicViewModel(
    private val useCaseMusic: MusicUseCase
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateListMusic>()
    val stateLiveData: LiveData<StateListMusic>
        get() = _stateLiveData

    private var _actionLiveData = MutableLiveData<EventListMusic>()
    val actionLiveData: LiveData<EventListMusic>
        get() = _actionLiveData

}
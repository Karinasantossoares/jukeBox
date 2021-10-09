package com.santos.jukebox.establishment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.useCase.MusicUseCase
import com.santos.jukebox.R
import com.santos.jukebox.establishment.data.EventRegisterMusic
import com.santos.jukebox.establishment.data.StateRegisterMusic

internal class ManagerMusicViewModel(
    private val useCaseMusic: MusicUseCase
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateRegisterMusic>()
    val stateLiveData: LiveData<StateRegisterMusic>
        get() = _stateLiveData

    private var _actionLiveData = MutableLiveData<EventRegisterMusic>()
    val actionLiveData: LiveData<EventRegisterMusic>
        get() = _actionLiveData

    init {
        _stateLiveData.value = StateRegisterMusic().showLoadingTypeMusics(true)
        findAll()
    }

    private fun findAll() {
//        useCaseMusic.findAll(
//            success = {
//                _stateLiveData.value = _stateLiveData.value?.showLoadingTypeMusics(false)
//                _stateLiveData.value = _stateLiveData.value?.showListTypeMusics(it)
//            },
//            error = {
//                _stateLiveData.value = _stateLiveData.value?.showLoadingTypeMusics(false)
//                _actionLiveData.value =
//                    _actionLiveData.value?.showMessage(context.getString(R.string.message_error_list_music))
//            }
//        )
    }

}
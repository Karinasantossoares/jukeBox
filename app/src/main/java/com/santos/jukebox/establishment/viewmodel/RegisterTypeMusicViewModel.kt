package com.santos.jukebox.establishment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.establishment.ui.state.StateTypeMusic
import com.santos.jukebox.establishment.useCase.RegisterTypeMusicUseCase

internal class RegisterTypeMusicViewModel(
    private val useCaseRegister: RegisterTypeMusicUseCase
) : ViewModel() {

    private var _liveData = MutableLiveData<StateTypeMusic>()
    val liveData: LiveData<StateTypeMusic>
        get() = _liveData

    private fun notifyLiveData(liveData: StateTypeMusic) {
        _liveData.postValue(liveData)
    }

    fun saveNewTypeMusic(typeMusic: String) {
        notifyLiveData(StateTypeMusic.Loading)
        useCaseRegister.saveTypeMusic(
            typeMusic = typeMusic,
            success = {
                notifyLiveData(StateTypeMusic.Success)
            },
            error = {
                it.localizedMessage?.let { errorMessage ->
                    notifyLiveData(StateTypeMusic.ShowMessage(errorMessage))
                }
            })
    }
}
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

    fun saveNewMusic(typeMusic: String) {
        notifyLiveData(StateTypeMusic.Loading)
        useCaseRegister.saveTypeMusic(
            typeMusic = typeMusic,
            success = {
                println("success save")
            },
            error = {
                it.localizedMessage?.let { errorMessage ->
                    notifyLiveData(StateTypeMusic.ShowMessage(errorMessage))
                }
            })
    }

    fun deleteTypeMusic(typeMusic: String) {
        notifyLiveData(StateTypeMusic.Loading)
        useCaseRegister.deleteTypeMusic(
            typeMusic = typeMusic,
            success = {
                println("success delete")
            },
            error = {
                it.localizedMessage?.let { errorMessage ->
                    notifyLiveData(StateTypeMusic.ShowMessage(errorMessage))
                }
            })
    }
    fun getAllTypesMusic() {
        notifyLiveData(StateTypeMusic.Loading)
        useCaseRegister.getAllTypeMusics(
            success = {
                notifyLiveData(StateTypeMusic.Success(it))
            },
            error = {
                it.localizedMessage?.let { errorMessage ->
                    notifyLiveData(StateTypeMusic.ShowMessage(errorMessage))
                }
            }
        )
    }
}
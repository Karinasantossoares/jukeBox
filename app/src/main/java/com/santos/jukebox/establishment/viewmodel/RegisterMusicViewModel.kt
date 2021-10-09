package com.santos.jukebox.establishment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.useCase.RegisterMusicUseCase
import com.santos.jukebox.R
import com.santos.jukebox.establishment.ui.action.EventRegisterMusic
import com.santos.jukebox.establishment.ui.state.StateRegisterMusic
import com.santos.jukebox.establishment.useCase.RegisterTypeMusicUseCase

internal class RegisterMusicViewModel(
    private val useCaseMusic: RegisterMusicUseCase,
    private val useCaseTypeMusic: RegisterTypeMusicUseCase,
    private val context: Context
) : ViewModel() {

    private var _stateLiveData = MutableLiveData(StateRegisterMusic())
    val stateLiveData: LiveData<StateRegisterMusic>
        get() = _stateLiveData

    private var _actionLiveData = MutableLiveData(EventRegisterMusic())
    val actionLiveData: LiveData<EventRegisterMusic>
        get() = _actionLiveData

    init {
        getAllTypeMusics()
    }

    private fun getAllTypeMusics() {
        _stateLiveData.value = _stateLiveData.value?.showLoadingTypeMusics(true)
        useCaseTypeMusic.getAllTypeMusics(
            success = {
                _stateLiveData.value = _stateLiveData.value?.showLoadingTypeMusics(false)
                _stateLiveData.value = _stateLiveData.value?.showListTypeMusics(it)
            },
            error = {
                _stateLiveData.value = _stateLiveData.value?.showLoadingTypeMusics(false)
                _actionLiveData.value =
                    _actionLiveData.value?.showMessage(context.getString(R.string.message_error_list_music))
            }
        )
    }

    fun saveNewMusic(music: RegisterMusicEstablishment) {
        _stateLiveData.value = _stateLiveData.value?.showLoadingMusics(true)

        useCaseMusic.saveMusic(
            music = music,
            success = {
                _stateLiveData.value = _stateLiveData.value?.showLoadingMusics(false)
                _actionLiveData.value =
                    _actionLiveData.value?.showMessage(context.getString(R.string.message_save_success))
            },
            error = {
                _stateLiveData.value = _stateLiveData.value?.showLoadingMusics(false)
                _actionLiveData.value =
                    _actionLiveData.value?.showMessage(context.getString(R.string.message_save_error))
            })
    }

    fun updateMusic(music: RegisterMusicEstablishment) {
        _stateLiveData.value = _stateLiveData.value?.showLoadingMusics(true)
        music.id?.let {
            useCaseMusic.updateMusic(idMusic = it,
                success = {
                    _stateLiveData.value = _stateLiveData.value?.showLoadingMusics(false)
                    _actionLiveData.value =
                        _actionLiveData.value?.showMessage(context.getString(R.string.message_delete_music_error))
                },
                error = {
                    _stateLiveData.value = _stateLiveData.value?.showLoadingMusics(false)
                    _actionLiveData.value =
                        _actionLiveData.value?.showMessage(context.getString(R.string.error_update_music))
                }
            )
        }
    }

    fun setTypeMusics(typeMusics: List<String>) {
        _stateLiveData.value?.setTypeMusics(typeMusics)
    }

//    private fun getAllMusics() {
//        _liveData.value = _liveData.value?.showLoadingMusics(true)
//        useCaseMusic.findMusic(
//            success = {
//                _liveData.value = _liveData.value?.showLoadingMusics(false)
//                _liveData.value = _liveData.value?.showListMusics(it)
//            },
//            error = {
//                _liveData.value = _liveData.value?.showLoadingMusics(false)
//                _liveData.value =
//                    _liveData.value?.showMessage(context.getString(R.string.message_error_list_music))
//            }
//        )
//    }


//    fun deleteMusic(music: RegisterMusicEstablishment) {
//        _liveData.value = _liveData.value?.showLoadingMusics(true)
//        music.id?.let {
//            useCaseMusic.deleteMusic(idMusic = it,
//                success = {
//                    _loadLiveData.value = false
//                    _messageLiveData.value =
//                        context.getString(R.string.message_delete_music_success)
//                },
//                error = {
//                    _loadLiveData.value = false
//                    _messageLiveData.value = context.getString(R.string.message_delete_music_error)
//                })
//        }
//    }

}
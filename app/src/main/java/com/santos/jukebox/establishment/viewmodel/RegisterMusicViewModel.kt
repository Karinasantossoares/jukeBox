package com.santos.jukebox.establishment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.R
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.ui.action.EventRegisterMusic
import com.santos.jukebox.establishment.ui.state.StateRegisterMusic
import com.santos.jukebox.establishment.useCase.MusicUseCase
import com.santos.jukebox.establishment.useCase.RegisterTypeMusicUseCase

internal class RegisterMusicViewModel(
    private val useCaseMusic: MusicUseCase,
    private val useCaseTypeMusic: RegisterTypeMusicUseCase,
    private val context: Context
) : ViewModel() {

    private var _stateLiveData = MutableLiveData<StateRegisterMusic>()
    val stateLiveData: LiveData<StateRegisterMusic>
        get() = _stateLiveData

    private var _actionLiveData = MutableLiveData<EventRegisterMusic>()
    val actionLiveData: LiveData<EventRegisterMusic>
        get() = _actionLiveData

    private var musicToDelete: String? = null

    init {
        _stateLiveData.value = StateRegisterMusic().showLoadingTypeMusics(true)
        getAllTypeMusics()
    }

    private fun getAllTypeMusics() {
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

    fun saveOrEditMusic(
        title: String,
        author: String,
        types: MutableList<String>,
        isVisible: Boolean
    ) {
        _stateLiveData.value = _stateLiveData.value?.showLoadingMusics(true)
        _stateLiveData.value = _stateLiveData.value?.newMusic(title, author, types, isVisible)
        if (stateLiveData.value?.isEditionMusic == false) {
            saveMusic()
        } else {
            updateMusic()
        }

    }

    private fun saveMusic() {
        _stateLiveData.value?.newMusic?.let { music ->
            useCaseMusic.saveMusic(
                music = music,
                success = {
                    _stateLiveData.value = _stateLiveData.value?.showLoadingMusics(false)
                    _actionLiveData.value = EventRegisterMusic.Success
                },
                error = {
                    _stateLiveData.value = _stateLiveData.value?.showLoadingMusics(false)
                    it.localizedMessage?.let { messageError ->
                        _actionLiveData.value = EventRegisterMusic.ShowMessage(messageError)
                    }
                })
        }
    }

    private fun updateMusic() {
        _stateLiveData.value?.newMusic?.let {
            useCaseMusic.updateMusic(music = it,
                success = {
                    _actionLiveData.value = EventRegisterMusic.Success
                },
                error = {
                    _stateLiveData.value = _stateLiveData.value?.showLoadingMusics(false)
                    it.localizedMessage?.let { messageError ->
                        _actionLiveData.value = EventRegisterMusic.ShowMessage(messageError)
                    }
                }
            )
        }
    }

    fun deleteTypeMusic() {
        _actionLiveData.value = EventRegisterMusic.HideDialog
        _stateLiveData.value = _stateLiveData.value?.showLoadingTypeMusics(true)
        musicToDelete?.let { typeDelete ->
            useCaseTypeMusic.deleteTypeMusic(
                typeDelete = typeDelete,
                success = {
                    _stateLiveData.value = _stateLiveData.value?.showLoadingTypeMusics(false)
                },
                error = {
                    _stateLiveData.value = _stateLiveData.value?.showLoadingTypeMusics(false)
                    it.localizedMessage?.let { errorMessage ->
                        _actionLiveData.value = EventRegisterMusic.ShowMessage(errorMessage)
                    }
                })
        }
    }

    fun setOnLongClick(music: String) {
        musicToDelete = music
        _actionLiveData.value = EventRegisterMusic.ShowDialogDialog(
            context.getString(R.string.alert),
            context.getString(R.string.message_toolbar_delete_type_music, music)
        )
    }

    fun setTypeMusics(typeMusics: List<String>) {
        _stateLiveData.value?.setTypeMusics(typeMusics)
    }

    fun setEditMusic(music: RegisterMusicEstablishment) {
        _stateLiveData.value = _stateLiveData.value?.editionMusic(music)
    }
}
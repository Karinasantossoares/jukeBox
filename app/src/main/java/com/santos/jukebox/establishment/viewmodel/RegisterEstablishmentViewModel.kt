package com.santos.establishment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.establishment.useCase.RegisterEstablishmentUseCase
import com.santos.jukebox.R
import io.reactivex.disposables.CompositeDisposable

internal class RegisterEstablishmentViewModel(
    private val useCaseRegister: RegisterEstablishmentUseCase,
    private val context: Context
) : ViewModel() {
    val disposable = CompositeDisposable()
    private var _successListLiveData = MutableLiveData<List<RegisterMusicEstablishment>>()
    val resultSuccess: LiveData<List<RegisterMusicEstablishment>>
        get() = _successListLiveData
    private var _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData
    private var _messageSuccessLiveData = MutableLiveData<String>()
    val successLiveData: LiveData<String>
        get() = _messageSuccessLiveData
    private var _loadLiveData = MutableLiveData<Boolean>()
    val loadResult: LiveData<Boolean>
        get() = _loadLiveData


    fun saveNewMusic(music: RegisterMusicEstablishment) {
        _loadLiveData.value = false
        useCaseRegister.saveMusic(music = music,
            success = {
                _loadLiveData.value = false
                _messageSuccessLiveData.value = context.getString(R.string.message_save_success)
            },
            error = {
                _loadLiveData.value = false
                _errorLiveData.value = context.getString(R.string.message_save_error)
            })
    }

    fun deleteMusic(music: RegisterMusicEstablishment) {
        _loadLiveData.value = true
        music.id?.let {
            useCaseRegister.deleteMusic(idMusic = it,
                success = {
                    _loadLiveData.value = false
                    _messageSuccessLiveData.value =
                        context.getString(R.string.message_delete_music_success)
                },
                error = {
                    _loadLiveData.value = false
                    _errorLiveData.value = context.getString(R.string.message_delete_music_error)
                })
        }
    }

    fun updateMusic(music: RegisterMusicEstablishment) {
        _loadLiveData.value = false
        music.id?.let {
            useCaseRegister.updateMusic(idMusic = it,
                success = {
                    _loadLiveData.value = false
                    _messageSuccessLiveData.value =
                        context.getString(R.string.message_update_success)
                },
                error = {
                    _loadLiveData.value = false
                    _errorLiveData.value = context.getString(R.string.error_update_music)
                }
            )
        }
    }

    fun findMusic() {
        _loadLiveData.value = false
        useCaseRegister.findMusic(
            success = {
                _successListLiveData.value = it
            },
            error = {
                _errorLiveData.value = context.getString(R.string.message_error_list_music)
            }
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
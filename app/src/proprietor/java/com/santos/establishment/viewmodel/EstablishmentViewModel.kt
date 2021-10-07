package com.santos.establishment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.establishment.data.Music
import com.santos.establishment.useCase.EstablishmentUseCase
import com.santos.jukebox.R
import io.reactivex.disposables.CompositeDisposable

internal class EstablishmentViewModel(
    private val useCase: EstablishmentUseCase,
    private val context: Context
) : ViewModel() {
    val disposable = CompositeDisposable()
    private var _successListLiveData = MutableLiveData<List<Music>>()
    val resultSuccess: LiveData<List<Music>>
        get() = _successListLiveData
    private var _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData
    private var _messageSuccessLiveData_ = MutableLiveData<String>()
    val successLiveData_: LiveData<String>
        get() = _messageSuccessLiveData_
    private var _loadLiveData = MutableLiveData<Boolean>()
    val loadResult: LiveData<Boolean>
        get() = _loadLiveData


    fun saveNewMusic(music: Music) {
        _loadLiveData.value = false
        useCase.saveMusic(music = music,
            success = {
                _loadLiveData.value = false
                _messageSuccessLiveData_.value = context.getString(R.string.message_save_success)
            },
            error = {
                _loadLiveData.value = false
                _errorLiveData.value = context.getString(R.string.message_save_error)
            })
    }

    fun deleteMusic(music: Music) {
        _loadLiveData.value = true
        music.id?.let {
            useCase.deleteMusic(idMusic = it,
                success = {
                    _loadLiveData.value = false
                    _messageSuccessLiveData_.value =
                        context.getString(R.string.message_delete_music_success)
                },
                error = {
                    _loadLiveData.value = false
                    _errorLiveData.value = context.getString(R.string.message_delete_music_error)
                })
        }
    }

    fun updateMusic(music: Music) {
        _loadLiveData.value = false
        music.id?.let {
            useCase.updateMusic(idMusic = it,
                success = {
                    _loadLiveData.value = false
                    _messageSuccessLiveData_.value =
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
        useCase.findMusic(
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
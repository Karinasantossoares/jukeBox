package com.santos.jukebox.establishment.useCase

import android.content.Context
import com.santos.jukebox.R
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.repository.TypeMusicRepository

internal class RegisterTypeMusicUseCase(
    private val context: Context,
    private val repository: TypeMusicRepository
) {

    fun verifyMusicAvaliable(
        musics: String,
        music: String,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) {
        when {
            musics.contains(music) -> {
                error.invoke(Exception(context.getString(R.string.error_message_always_type)))
            }
            music.isEmpty() -> {
                error.invoke(Exception(context.getString(R.string.error_message_characteres)))
            }
            music.contains("/")
                    || music.contains(".")
                    || music.contains(",")
                    || music.contains("*") -> {
                error.invoke(Exception(context.getString(R.string.error_default_name_characteres)))
            }
            else -> {
                success.invoke()
            }
        }
    }

    fun saveTypeMusic(
        typeMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) {
        repository.saveTypeMusic(success, error, typeMusic)
    }

    fun deleteTypeMusic(
        typeDelete: String,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) = repository.deleteTypeMusic(typeDelete, success, error)

    fun getAllTypeMusics(
        success: (List<String>) -> Unit,
        error: (Exception) -> Unit
    ) = repository.getAllTypes(success, error)
}
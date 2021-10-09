package com.santos.jukebox.establishment.useCase

import android.content.Context
import com.santos.jukebox.R
import com.santos.jukebox.establishment.data.MusicEstablishmentResponse
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.repository.MusicRepository

internal class MusicUseCase(
    private val repository: MusicRepository,
    private val context: Context
) {

    fun saveMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        music: RegisterMusicEstablishment
    ) {
        when {
            music.title.isEmpty() -> {
                error.invoke(Exception(context.getString(R.string.title_not_empty)))
            }
            music.types.isEmpty() -> {
                error.invoke(Exception(context.getString(R.string.types_music_empty)))
            }
            else -> {
                return repository.saveMusic(success, error, music)
            }
        }
    }

    fun updateMusic(
        idMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
    ) = repository.updateMusic(idMusic, success, error)

    fun deleteMusic(
        idMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) = repository.deleteMusic(idMusic, success, error)

    fun findAll(
        success: (List<MusicEstablishmentResponse>) -> Unit,
        error: (Exception) -> Unit
    ) = repository.getAllMusics(success, error)
}
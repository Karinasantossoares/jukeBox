package com.santos.jukebox.establishment.useCase

import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.repository.MusicRepository

internal class RegisterMusicUseCase(
    private val repository: MusicRepository
) {

    fun saveMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        music: RegisterMusicEstablishment
    ) = repository.saveMusic(success, error, music)

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

    fun findMusic(
        success: (List<RegisterMusicEstablishment>) -> Unit,
        error: (Exception) -> Unit
    ) = repository.findMusics(success, error)
}
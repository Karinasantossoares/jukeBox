package com.santos.establishment.useCase

import com.santos.establishment.data.Music
import com.santos.establishment.repository.EstablishmentRepository

internal class EstablishmentUseCase(
    private val repository: EstablishmentRepository
) {

    fun saveMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        music: Music
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
        success: (List<Music>) -> Unit,
        error: (Exception) -> Unit
    ) = repository.findMusics(success, error)
}
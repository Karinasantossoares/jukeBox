package com.santos.jukebox.establishment.useCase

import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.repository.MusicRepository

class MusicQueueUseCase(
    private val repository: MusicRepository
) {

    fun getAllQueueMusics(
        success: (List<RegisterMusicEstablishment>) -> Unit,
        error: (Exception) -> Unit
    ) = repository.getAllQueueMusics(success, error)

    fun markAsFinish(
        id: String,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) = repository.deleteMusicQueue(id, success, error)

}
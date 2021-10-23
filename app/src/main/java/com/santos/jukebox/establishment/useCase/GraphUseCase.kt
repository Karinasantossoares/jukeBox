package com.santos.jukebox.establishment.useCase

import android.content.Context
import com.santos.jukebox.R
import com.santos.jukebox.client.data.TopMusicsResponse
import com.santos.jukebox.client.repository.HistoryRepository
import com.santos.jukebox.establishment.data.MusicEstablishmentResponse
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.repository.MusicRepository

class GraphUseCase(
    private val repository: HistoryRepository
) {
    fun findAllHistory(
        success: (TopMusicsResponse) -> Unit,
        error: (Throwable) -> Unit,
    ) = repository.findAllHistory(success, error)


}
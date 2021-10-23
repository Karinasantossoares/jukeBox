package com.santos.jukebox.client.repository

import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.remote.HistoryFirebase

class HistoryRepository(
    private val repository: HistoryFirebase
) {

    fun addMusicQueue(music: Music) = repository.addMusicHistory(music)

}
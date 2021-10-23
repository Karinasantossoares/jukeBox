package com.santos.jukebox.client.repository

import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.data.MusicResponse
import com.santos.jukebox.client.remote.ClientFirebase

class ClientRepository(
    private val firebaseClient: ClientFirebase
) {

    fun getVisibleMusic(
        success: (List<MusicResponse>) -> Unit,
        error: (Exception) -> Unit
    ) {
        firebaseClient.getVisibleMusic(
            success = { musicList ->
                val response = mutableListOf<MusicResponse>()
                musicList.forEach { currentMusic ->
                    currentMusic.types.forEach { currentType ->
                        if (response.find { it.type == currentType } == null) {
                            response.add(MusicResponse(currentType, mutableListOf()))
                        }

                        response.find { it.type == currentType }?.musics?.add(currentMusic)
                    }
                }
                success.invoke(response)
            }, error
        )
    }

    fun addMusicQueue(
        music: Music,
        success: () -> Unit,
        error: (Exception) -> Unit
    ) {
        firebaseClient.addMusicQueue(
            music = music,
            success = success,
            error = error
        )
    }
}
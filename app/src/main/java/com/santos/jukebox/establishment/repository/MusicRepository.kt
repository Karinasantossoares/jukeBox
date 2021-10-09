package com.santos.jukebox.establishment.repository

import com.santos.jukebox.establishment.data.MusicEstablishmentResponse
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.remote.FirebaseMusic

internal class MusicRepository(
    private val firebaseMusic: FirebaseMusic
) {

    fun saveMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        music: RegisterMusicEstablishment
    ) {
        firebaseMusic.saveMusic(success, error, music)
    }

    fun updateMusic(
        idMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
    ) {
        firebaseMusic.updateMusic(idMusic, success, error)
    }

    fun deleteMusic(
        idMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
    ) {
        firebaseMusic.deleteMusic(idMusic, success, error)
    }

    fun getAllMusics(
        success: (List<MusicEstablishmentResponse>) -> Unit,
        error: (Exception) -> Unit
    ) {
        firebaseMusic.getAllMusics(
            success = { musicList ->
                val response = mutableListOf<MusicEstablishmentResponse>()
                musicList.forEach { currentMusic ->
                    currentMusic.types.forEach { currentType ->
                        if (response.find { it.type == currentType } == null) {
                            response.add(MusicEstablishmentResponse(currentType, mutableListOf()))
                        }

                        response.find { it.type == currentType }?.musics?.add(currentMusic)
                    }
                }
                success.invoke(response)
            }, error
        )
    }
}
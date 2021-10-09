package com.santos.jukebox.establishment.repository

import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.remote.FirebaseMusic
import com.santos.jukebox.establishment.remote.FirebaseTypeMusic

internal class TypeMusicRepository(
    private val firebaseMusic: FirebaseTypeMusic
) {

    fun saveTypeMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        music: String
    ) {
        firebaseMusic.saveTypeMusic(success, error, music)
    }

    fun updateTypeMusic(
        oldTypeMusic: String,
        newTypeMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
    ) {
        firebaseMusic.updateTypeMusic(oldTypeMusic,newTypeMusic, success, error)
    }

    fun deleteTypeMusic(
        idMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
    ) {
        firebaseMusic.deleteTypeMusic(idMusic, success, error)
    }

    fun getAllTypes(
        success: (List<String>) -> Unit,
        error: (Exception) -> Unit
    ) {
        firebaseMusic.getAllTypeMusics(success, error)
    }
}
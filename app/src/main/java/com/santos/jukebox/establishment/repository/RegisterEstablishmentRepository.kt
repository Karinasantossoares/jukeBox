package com.santos.jukebox.establishment.repository

import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.persistence.FirebaseEstablishment

const val MUSIC = "MUSIC"

internal class EstablishmentRepository(
    private val firebaseEstablishment: FirebaseEstablishment
) {

    fun saveMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        music: RegisterMusicEstablishment
    ) {
        firebaseEstablishment.saveMusic(success, error, music)
    }

    fun updateMusic(
        idMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
    ) {
        firebaseEstablishment.updateMusic(idMusic, success, error)
    }

    fun deleteMusic(
        idMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
    ) {
        firebaseEstablishment.deleteMusic(idMusic, success, error)
    }

    fun findMusics(
        success: (List<RegisterMusicEstablishment>) -> Unit,
        error: (Exception) -> Unit
    ) {
        firebaseEstablishment.findMusics(success, error)
    }
}
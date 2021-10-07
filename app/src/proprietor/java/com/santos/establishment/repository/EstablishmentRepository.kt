package com.santos.establishment.repository

import com.santos.establishment.data.Music
import com.santos.establishment.persistence.FirebaseEstablishment

const val MUSIC = "MUSIC"

internal class EstablishmentRepository(
    private val firebaseEstablishment: FirebaseEstablishment
) {

    fun saveMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        music: Music
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
        success: (List<Music>) -> Unit,
        error: (Exception) -> Unit
    ) {
        firebaseEstablishment.findMusics(success, error)
    }
}
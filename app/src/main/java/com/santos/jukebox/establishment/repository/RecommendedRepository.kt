package com.santos.jukebox.establishment.repository

import com.santos.jukebox.client.data.SuggestionResponse
import com.santos.jukebox.client.remote.SuggestionFirebase

class RecommendedRepository(
    private val recommendedFirebase: SuggestionFirebase
) {
    fun getAllRecommendedMusic(
        success: (List<SuggestionResponse>) -> Unit,
        error: (Exception) -> Unit,
    ) {
        recommendedFirebase.getAllSuggestionMusic(
            success, error
        )
    }

    fun deleteRecommendedMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        id: String
    ) {
        recommendedFirebase.deleteRecommendedMusic(
            success, error, id
        )
    }
}
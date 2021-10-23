package com.santos.jukebox.client.repository

import com.santos.jukebox.client.data.SuggestionResponse
import com.santos.jukebox.client.remote.SuggestionFirebase

class SuggestionRepository(
    val suggestionFirebase: SuggestionFirebase
) {

    fun addSuggestionMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        suggestionResponse: SuggestionResponse
    ) {
        suggestionFirebase.addSuggestionMusic(
            success, error, suggestionResponse
        )
    }

    fun getAllSuggestionMusic(
        success: (List<SuggestionResponse>) -> Unit,
        error: (Exception) -> Unit,
    ) {
        suggestionFirebase.getAllSuggestionMusic(
            success, error
        )
    }
}
package com.santos.jukebox.client.usecase

import android.content.Context
import com.santos.jukebox.R
import com.santos.jukebox.client.data.SuggestionResponse
import com.santos.jukebox.client.repository.SuggestionRepository
import com.santos.jukebox.client.ui.state.StateSuggestion

class SuggestionUseCase(
    val repository: SuggestionRepository,
    val context: Context
) {

    fun addSuggestionMusic(
        success: (String) -> Unit,
        error: (Exception) -> Unit,
        suggestionResponse: SuggestionResponse
    ) {
        repository.addSuggestionMusic(
            success = {
                success.invoke(context.getString(R.string.message_send_success))
            },
            error = {
                error.invoke(Exception(context.getString(R.string.message_send_error)))
            },
            suggestionResponse
        )
    }
}
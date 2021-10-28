package com.santos.jukebox.client.ui.state

import com.santos.jukebox.client.data.MusicResponse
import com.santos.jukebox.client.data.SuggestionResponse

sealed class StateSuggestion {
    object Loading : StateSuggestion()
    data class ShowMessage(val message: String) : StateSuggestion()
    object SuccessSuggestionMusic : StateSuggestion()
}
package com.santos.jukebox.client.ui.state

import com.santos.jukebox.client.data.SuggestionResponse

sealed class StateSuggestion {
    data class SuccessListMusic(val listMusic: List<SuggestionResponse>) : StateSuggestion()
    object Loading : StateSuggestion()
    data class ShowMessage(val message: String) : StateSuggestion()
}
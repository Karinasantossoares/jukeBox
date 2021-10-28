package com.santos.jukebox.client.ui.state

import com.santos.jukebox.client.data.SuggestionResponse

sealed class StateRecommended {
    object Loading : StateRecommended()
    data class ShowMessage(val message: String) : StateRecommended()
    data class SuccessListMusic(val listMusic: List<SuggestionResponse>) : StateRecommended()
}
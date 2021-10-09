package com.santos.jukebox.client.ui.state

import com.santos.jukebox.client.data.MusicResponse

sealed class StateClient {
    data class SuccessListMusic(val listMusic: List<MusicResponse>) : StateClient()
    object Loading : StateClient()
    data class ShowMessage(val message: String) : StateClient()

}

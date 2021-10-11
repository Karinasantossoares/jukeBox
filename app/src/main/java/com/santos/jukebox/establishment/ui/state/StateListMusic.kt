package com.santos.jukebox.establishment.ui.state

import com.santos.jukebox.establishment.data.MusicEstablishmentResponse

sealed class StateListMusic {
    data class SuccessListMusic(val musics: List<MusicEstablishmentResponse>) : StateListMusic()
    data class ShowMessage(val message: String) : StateListMusic()
    data class ShowMessageId(val int: Int) : StateListMusic()
    object Loading : StateListMusic()
}
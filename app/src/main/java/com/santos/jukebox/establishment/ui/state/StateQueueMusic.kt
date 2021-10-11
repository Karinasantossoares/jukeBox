package com.santos.jukebox.establishment.ui.state

import com.santos.jukebox.establishment.data.MusicEstablishmentResponse
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment

sealed class StateQueueMusic {
    data class SuccessListMusic(val musics: List<RegisterMusicEstablishment>) : StateQueueMusic()
    data class ShowMessage(val message: String) : StateQueueMusic()
    object EmptyList : StateQueueMusic()
    object Loading : StateQueueMusic()
}
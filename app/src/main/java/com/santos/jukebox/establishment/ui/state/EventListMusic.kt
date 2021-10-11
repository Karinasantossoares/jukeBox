package com.santos.jukebox.establishment.ui.state

import com.santos.jukebox.establishment.data.RegisterMusicEstablishment

sealed class EventListMusic {
    data class EditMusic(val music: RegisterMusicEstablishment) : EventListMusic()
    data class DeleteMusic(val music: RegisterMusicEstablishment) : EventListMusic()
}
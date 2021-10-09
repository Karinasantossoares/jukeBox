package com.santos.jukebox.establishment.data

data class MusicEstablishmentResponse(
    val type: String,
    val musics: MutableList<RegisterMusicEstablishment> = mutableListOf(),
)
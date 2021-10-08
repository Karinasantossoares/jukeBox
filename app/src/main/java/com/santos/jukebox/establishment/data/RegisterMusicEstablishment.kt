package com.santos.jukebox.establishment.data

internal data class RegisterMusicEstablishment(
    var id : String? = null,
    val title: String,
    val author: String,
    val description: String
)
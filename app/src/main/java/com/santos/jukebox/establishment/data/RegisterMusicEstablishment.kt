package com.santos.jukebox.establishment.data

data class RegisterMusicEstablishment(
    var id: String? = null,
    val title: String,
    val author: String,
    val types: List<String> = listOf()
)
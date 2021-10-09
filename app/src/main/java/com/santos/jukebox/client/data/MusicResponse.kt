package com.santos.jukebox.client.data

data class MusicResponse(
    val type: String,
    val musics: List<Music>,
)

data class Music(
    var id: String? = null,
    val title: String,
    val author: String,
    val type: List<String> = listOf()
)
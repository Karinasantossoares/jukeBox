package com.santos.jukebox.client.data

data class MusicResponse(
    val type: String,
    val musics: MutableList<Music> = mutableListOf(),
)

data class Music(
    var id: String? = null,
    val title: String = "",
    val author: String = "",
    val types: List<String> = listOf(),
    val isVisible: Boolean = true
)
package com.santos.jukebox.client.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MusicResponse(
    val type: String,
    val musics: MutableList<Music> = mutableListOf(),
)

data class MusicCount(
    var music: Music,
    var count: Int
)

@Parcelize
data class Music(
    var id: String? = null,
    val title: String = "",
    val author: String = "",
    var requestName: String? = null,
    val types: List<String> = listOf(),
    val visible: Boolean = true
) : Parcelable
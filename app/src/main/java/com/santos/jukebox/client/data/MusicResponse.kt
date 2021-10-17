package com.santos.jukebox.client.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MusicResponse(
    val type: String,
    val musics: MutableList<Music> = mutableListOf(),
)

@Parcelize
data class Music(
    var id: String? = null,
    val title: String = "",
    val author: String = "",
    val types: List<String> = listOf(),
    val visible: Boolean = true
) : Parcelable
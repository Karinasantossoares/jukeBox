package com.santos.jukebox.establishment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterMusicEstablishment(
    var id: String? = null,
    val title: String = "",
    val author: String = "",
    var requestName: String? = null,
    val types: List<String> = listOf(),
    val visible: Boolean = true
) : Parcelable
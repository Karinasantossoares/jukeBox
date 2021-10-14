package com.santos.jukebox.establishment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterMusicEstablishment(
    var id: String? = null,
    val title: String = "",
    val author: String = "",
    val types: List<String> = listOf(),
    val isVisible: Boolean = true,
    val visibleForClient: Boolean? = null
) : Parcelable
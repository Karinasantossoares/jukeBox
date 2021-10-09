package com.santos.jukebox.establishment.data

sealed class StateTypeMusic {
    data class Success(val types: List<String>) : StateTypeMusic()
    data class ShowMessage(val message: String) : StateTypeMusic()
    object Loding : StateTypeMusic()
}

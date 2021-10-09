package com.santos.jukebox.establishment.data

sealed class StateTypeMusic {
    object Success : StateTypeMusic()
    data class ShowMessage(val message: String) : StateTypeMusic()
    object Loding : StateTypeMusic()
}

package com.santos.jukebox.establishment.ui.state

sealed class StateTypeMusic {
    object Success : StateTypeMusic()
    data class ShowMessage(val message: String) : StateTypeMusic()
    object Loading : StateTypeMusic()
}

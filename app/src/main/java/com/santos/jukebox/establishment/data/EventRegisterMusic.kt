package com.santos.jukebox.establishment.data

data class EventRegisterMusic(
    var message: String = "",
) {
    fun showMessage(message: String) = copy(
        message = message
    )
}
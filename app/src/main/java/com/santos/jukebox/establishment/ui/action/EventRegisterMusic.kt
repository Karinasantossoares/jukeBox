package com.santos.jukebox.establishment.ui.action

data class EventRegisterMusic(
    var message: String = "",
) {
    fun showMessage(message: String) = copy(
        message = message
    )
}
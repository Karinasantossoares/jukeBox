package com.santos.jukebox.client.ui.action

data class EventClient(
    var message: String = "",
) {
    fun showMessage(message: String) = copy(
        message = message
    )
}

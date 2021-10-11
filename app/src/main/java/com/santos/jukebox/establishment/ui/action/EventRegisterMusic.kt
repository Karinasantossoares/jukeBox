package com.santos.jukebox.establishment.ui.action

sealed class EventRegisterMusic {
    data class ShowMessage(var message: String = "") : EventRegisterMusic()
    object Success : EventRegisterMusic()
    object HideDialog : EventRegisterMusic()
    data class ShowDialogDialog(
        val title: String,
        val message: String
    ) : EventRegisterMusic()

    fun showMessage(message: String) = ShowMessage(message)
}
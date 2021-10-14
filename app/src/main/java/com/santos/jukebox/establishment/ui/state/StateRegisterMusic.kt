package com.santos.jukebox.establishment.ui.state

import com.santos.jukebox.establishment.data.RegisterMusicEstablishment

data class StateRegisterMusic(
    var newMusic: RegisterMusicEstablishment = RegisterMusicEstablishment(
        title = "",
        author = ""
    ),
    var allTypeMusics: List<String> = listOf(),
    var isLoadingSaveMusic: Boolean = false,
    var isLoadingGetTypeMusics: Boolean = false,
    var isEditionMusic: Boolean = false,
    var isMusicCheckVisible: Boolean = false
) {

    fun editionMusic(music: RegisterMusicEstablishment) = copy(
        isEditionMusic = true,
        newMusic = music
    )

    fun showLoadingMusics(loading: Boolean) = copy(
        isLoadingSaveMusic = loading
    )

    fun newMusic(
        title: String,
        author: String,
        types: List<String>
    ) = copy(
        newMusic = newMusic.copy(
            title = title,
            author = author,
            types = types,
            visibleForClient = isMusicCheckVisible
        )
    )

    fun setTypeMusics(allTypeMusics: List<String>) = copy(
        allTypeMusics = allTypeMusics
    )

    fun showListTypeMusics(typeMusics: List<String>) = copy(
        allTypeMusics = typeMusics
    )

    fun showLoadingTypeMusics(loading: Boolean) = copy(
        isLoadingGetTypeMusics = loading
    )

    fun setVisibilityMusic(checked: Boolean) = copy(
        isMusicCheckVisible = checked
    )
}
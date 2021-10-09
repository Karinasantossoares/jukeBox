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
) {
    fun showLoadingMusics(loading: Boolean) = copy(
        isLoadingSaveMusic = loading
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
}
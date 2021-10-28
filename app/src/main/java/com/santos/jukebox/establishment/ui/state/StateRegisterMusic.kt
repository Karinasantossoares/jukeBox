package com.santos.jukebox.establishment.ui.state

import com.santos.jukebox.client.data.SuggestionResponse
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
    var isRegistrationRecommendedMusic: Boolean = false,
    var suggestionMusic: SuggestionResponse? = null
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
        types: List<String>,
        isVisible: Boolean
    ) = copy(
        newMusic = newMusic.copy(
            title = title,
            author = author,
            types = types,
            visible = isVisible
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

    fun registerRecommnededMusic(suggestion: SuggestionResponse) = copy(
        suggestionMusic = suggestion,
        isRegistrationRecommendedMusic = true,
        newMusic = newMusic.copy(
            title = suggestion.nameMusic ?: ""
        )
    )
}
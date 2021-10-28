package com.santos.jukebox.establishment.useCase

import android.content.Context
import com.santos.jukebox.R
import com.santos.jukebox.client.data.SuggestionResponse
import com.santos.jukebox.establishment.repository.RecommendedRepository

class RecommendedUseCase(
    private val repository: RecommendedRepository,
    private val context: Context
) {

    fun getAllSuggestionMusic(
        success: (List<SuggestionResponse>) -> Unit,
        error: (Exception) -> Unit,
    ) {
        repository.getAllRecommendedMusic(
            success = {
                success.invoke(it)
            },
            error = {
                error.invoke(
                    Exception(
                        context.getString(R.string.message_error_get_suggestion)
                    )
                )
            }
        )
    }

    fun deleteRecommendedMusic(
        success: (String) -> Unit,
        error: (Exception) -> Unit,
        idMusic: String
    ) {
        repository.deleteRecommendedMusic(
            success = {
                success.invoke(context.getString(R.string.message_success_delete_recommended))
            },
            error = {
                error.invoke(
                    Exception(
                        context.getString(R.string.message_error_delete_recommended)
                    )
                )
            },
            id = idMusic
        )
    }
}
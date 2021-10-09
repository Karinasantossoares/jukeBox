package com.santos.jukebox.client.usecase

import android.content.Context
import com.santos.jukebox.R
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.data.MusicResponse
import com.santos.jukebox.client.repository.ClientRepository

class ClientUseCase(
    private val repository: ClientRepository,
    private val context: Context
) {

    fun getVisibleMusic(
        success: (List<MusicResponse>) -> Unit,
        error: (Exception) -> Unit
    ) {
        repository.getVisibleMusic(success, error ={
            error.invoke(Exception(context.getString(R.string.error_list_music_client)))
        })
    }

    fun addMusicQueue(
        isChecked: Boolean,
        music: Music,
        success: (String) -> Unit,
        error: (Exception) -> Unit
    ) {
        if (isChecked) {
            repository.addMusicQueue(music,
                success = {
                    success.invoke(context.getString(R.string.message_success_add_queue_client))
                },
                error = {
                  error.invoke(Exception(context.getString(R.string.message_error_add_queue_client)))
                })
        }
    }
}
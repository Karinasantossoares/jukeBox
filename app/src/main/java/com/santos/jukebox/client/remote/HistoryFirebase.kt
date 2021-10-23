package com.santos.jukebox.client.remote

import com.google.firebase.database.DatabaseReference
import com.santos.jukebox.client.data.Music

class HistoryFirebase(
    databaseReference: DatabaseReference
) {

    private val database = databaseReference.child(HISTORY_MUSIC)


    fun addMusicHistory(
        music: Music
    ) {
        music.id?.let { id ->
            database
                .child(id)
                .setValue(music)
        }
    }

    companion object {
        private const val HISTORY_MUSIC = "HISTORY"
    }
}
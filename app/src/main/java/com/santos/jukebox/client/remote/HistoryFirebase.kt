package com.santos.jukebox.client.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.data.TopMusicsResponse
import com.santos.jukebox.establishment.data.MusicEstablishmentResponse

class HistoryFirebase(
    databaseReference: DatabaseReference
) {

    private val database = databaseReference.child(HISTORY_MUSIC)


    fun addMusicHistory(
        music: Music
    ) {
        val nextId = database.push().key
        nextId?.let { id ->
            database
                .child(id)
                .setValue(music)
        }
    }

    fun findAllHistory(
        success: (List<Music>) -> Unit,
        error: (Throwable) -> Unit
    ) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                success.invoke(snapshot.children.mapNotNull {
                    it.getValue(Music::class.java)
                })
            }

            override fun onCancelled(errorFirebase: DatabaseError) {
                error.invoke(errorFirebase.toException())
            }
        })
    }

    fun deleteAll(
        success: () -> Unit,
        error: (Throwable) -> Unit
    ) {
        database
            .setValue(null)
            .addOnCompleteListener {
                success.invoke()
            }
            .addOnFailureListener {
                error.invoke(it)
            }
    }

    companion object {
        private const val HISTORY_MUSIC = "HISTORY"
    }
}
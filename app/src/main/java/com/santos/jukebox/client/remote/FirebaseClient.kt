package com.santos.jukebox.client.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.santos.jukebox.client.data.Music

class FirebaseClient(
    databaseReference: DatabaseReference
) {

    private val database = databaseReference.child(QUEUE_MUSIC)

    fun getVisibleMusic(
        success: (List<Music>) -> Unit,
        error: (Exception) -> Unit
    ) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(Music::class.java)
                }
                success.invoke(list.filter {
                    it.isVisible
                })
            }

            override fun onCancelled(errorFirebase: DatabaseError) {
                error.invoke(errorFirebase.toException())
            }
        })
    }

    fun addMusicQueue(
        success: () -> Unit,
        error: (Exception) -> Unit,
        music: Music
    ) {
        database.child(QUEUE_MUSIC)
            .setValue(music)
            .addOnCompleteListener {
                success.invoke()
            }
            .addOnFailureListener {
                error.invoke(it)
            }
    }

    companion object {
        private const val QUEUE_MUSIC = "QUEUE_MUSIC"
    }
}
package com.santos.jukebox.establishment.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment

class FirebaseQueueMusic(
    databaseMusic: DatabaseReference
) {
    private val database = databaseMusic.child(QUEUE_MUSIC)


    fun getQueueMusics(
        success: (List<RegisterMusicEstablishment>) -> Unit,
        error: (Exception) -> Unit
    ) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(RegisterMusicEstablishment::class.java)
                }
                success.invoke(list)
            }

            override fun onCancelled(errorFirebase: DatabaseError) {
                error.invoke(errorFirebase.toException())
            }
        })
    }

    fun deleteMusicQueue(
        idMusic: String, success: () -> Unit,
        error: (Exception) -> Unit
    ) {
        database.child(idMusic)
            .removeValue()
            .addOnSuccessListener {
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
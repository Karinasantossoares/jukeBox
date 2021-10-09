package com.santos.jukebox.establishment.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment

internal class FirebaseTypeMusic(
    private val databaseTypeMusic: DatabaseReference
) {
    private val database = databaseTypeMusic.child(TYPE_MUSIC)

    fun saveTypeMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        music: String
    ) {
        database.child(TYPE_MUSIC)
            .child(music)
            .setValue(music)
            .addOnCompleteListener {
                success.invoke()
            }
            .addOnFailureListener {
                error.invoke(it)
            }
    }

    fun updateTypeMusic(
        oldTypeMusic: String,
        newTypeMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
    ) {
        database.child(oldTypeMusic)
            .setValue(newTypeMusic)
            .addOnSuccessListener {
                success.invoke()
            }
            .addOnFailureListener {
                error.invoke(it)
            }
    }

    fun deleteTypeMusic(
        idMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
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

    fun getAllTypeMusics(
        success: (List<String>) -> Unit,
        error: (Exception) -> Unit
    ) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(String::class.java)
                }
                success.invoke(list)
            }

            override fun onCancelled(errorFirebase: DatabaseError) {
                error.invoke(errorFirebase.toException())
            }
        })
    }

    companion object {
        private const val TYPE_MUSIC = "TYPE_MUSIC"
    }
}
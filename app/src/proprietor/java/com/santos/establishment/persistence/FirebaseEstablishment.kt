package com.santos.establishment.persistence

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.santos.establishment.data.Music
import com.santos.establishment.repository.MUSIC

internal class FirebaseEstablishment(
    private val databaseEstablishment: DatabaseReference
) {

    fun saveMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        music: Music
    ) {
        val nextId = databaseEstablishment.push().key
        music.id = nextId.toString()
        databaseEstablishment.child(MUSIC)
            .child(nextId.toString())
            .setValue(music)
            .addOnCompleteListener {
                success.invoke()
            }
            .addOnFailureListener {
                error.invoke(it)
            }
    }

    fun updateMusic(
        idMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
    ) {
        databaseEstablishment.child(idMusic)
            .setValue(idMusic)
            .addOnSuccessListener {
                success.invoke()
            }
            .addOnFailureListener {
                error.invoke(it)
            }
    }

    fun deleteMusic(
        idMusic: String,
        success: () -> Unit,
        error: (Exception) -> Unit,
    ) {
        databaseEstablishment.child(idMusic)
            .removeValue()
            .addOnSuccessListener {
                success.invoke()
            }
            .addOnFailureListener {
                error.invoke(it)
            }
    }

    fun findMusics(
        success: (List<Music>) -> Unit,
        error: (Exception) -> Unit
    ) {
        databaseEstablishment.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(Music::class.java)
                }
                success.invoke(list)
            }

            override fun onCancelled(errorFirebase: DatabaseError) {
                error.invoke(errorFirebase.toException())
            }
        })
    }
}
package com.santos.jukebox.client.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.santos.jukebox.client.data.SuggestionResponse

class SuggestionFirebase(
    databaseReference: DatabaseReference
) {

    private val database = databaseReference.child(SUGGESTION_MUSIC)

    fun addSuggestionMusic(
        success: () -> Unit,
        error: (Exception) -> Unit,
        suggestionResponse: SuggestionResponse
    ) {
        database.setValue(suggestionResponse)
            .addOnCompleteListener {
                success.invoke()
            }
            .addOnFailureListener {
                error.invoke(it)
            }
    }

    fun getAllSuggestionMusic(
        success: (List<SuggestionResponse>) -> Unit,
        error: (Exception) -> Unit,
    ) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(SuggestionResponse::class.java)
                }
                success.invoke(list)
            }

            override fun onCancelled(errorFirebase: DatabaseError) {
                error.invoke(errorFirebase.toException())
            }
        })
    }

    companion object {
        private const val SUGGESTION_MUSIC = "SUGGESTION_MUSIC"
    }
}
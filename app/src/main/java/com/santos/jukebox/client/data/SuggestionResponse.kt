package com.santos.jukebox.client.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class SuggestionResponse(
    var id: String? = null,
    val nameMusic: String? = "",
    val date: Date = Date()
): Parcelable {

    fun formattedDate(): String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
}
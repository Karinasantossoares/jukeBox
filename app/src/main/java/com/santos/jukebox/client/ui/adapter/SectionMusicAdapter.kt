package com.santos.jukebox.client.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.R
import com.santos.jukebox.client.data.MusicResponse
import com.santos.jukebox.client.ui.adapter.viewholder.SectionViewHolder

class SectionMusicAdapter(val listener: () -> Unit) :
    RecyclerView.Adapter<SectionViewHolder>() {

    var listMusic = emptyList<MusicResponse>()
        set(value) {
            field
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        return SectionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_section_music, parent, false),
            listener = listener
        )
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(listMusic[position])
    }

    override fun getItemCount(): Int = listMusic.count()

}
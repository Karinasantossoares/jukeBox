package com.santos.jukebox.client.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.client.data.MusicResponse
import com.santos.jukebox.client.ui.adapter.CategoryAdapter
import com.santos.jukebox.databinding.ItemSectionMusicBinding

class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = ItemSectionMusicBinding.bind(itemView)

    fun bind(music: MusicResponse) {
        binding.titleTypeMusic.text = music.type
        binding.recyclerSectionMusic.adapter = CategoryAdapter().apply {

        }

    }
}
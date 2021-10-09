package com.santos.jukebox.client.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.databinding.ItemCategoryMusicBinding

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemCategoryMusicBinding.bind(itemView)

    fun bind(music: Music) {
        binding.textNameMusic.text = music.title
    }

}
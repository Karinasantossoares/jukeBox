package com.santos.jukebox.client.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.databinding.ItemCategoryMusicBinding

class CategoryViewHolder(
    itemView: View,
    private val listener: (Music) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemCategoryMusicBinding.bind(itemView)

    fun bind(music: Music) {
        if (music.visible) {
            binding.textNameMusic.text = music.title
            binding.cardView.setOnClickListener {
                listener.invoke(music)
            }
        }

    }

}
package com.santos.jukebox.establishment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.R
import com.santos.jukebox.client.data.SuggestionResponse
import com.santos.jukebox.databinding.ItemCategoryMusicBinding

class RecommendedMusicAdapter :
    RecyclerView.Adapter<RecommendedMusicAdapter.ItemMusicViewHolder>() {

    var listRecommendedMusic = emptyList<SuggestionResponse>()
        set(value) {
            field
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMusicViewHolder {
        return ItemMusicViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_music, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemMusicViewHolder, position: Int) {
        holder.bind(listRecommendedMusic[position])
    }

    override fun getItemCount() = listRecommendedMusic.count()

    inner class ItemMusicViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCategoryMusicBinding.bind(itemView)

        fun bind(music: SuggestionResponse) {
            binding.textNameMusic.text = music.text
        }

    }

}

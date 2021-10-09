package com.santos.jukebox.establishment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.R
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.ui.adapter.viewholder.CategoryViewHolder
import com.santos.jukebox.databinding.ItemCategoryMusicBinding
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment

class ItemMusicAdapter(
    val tapListener: ((RegisterMusicEstablishment) -> Unit)?,
    val longClickListener: ((RegisterMusicEstablishment) -> Unit)?,
) : RecyclerView.Adapter<ItemMusicAdapter.ItemMusicViewHolder>() {

    var listNameMusic = emptyList<RegisterMusicEstablishment>()
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
        holder.bind(listNameMusic[position])
    }

    override fun getItemCount() = listNameMusic.count()

    inner class ItemMusicViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCategoryMusicBinding.bind(itemView)

        fun bind(music: RegisterMusicEstablishment) {
            binding.textNameMusic.text = music.title
        }

    }


}
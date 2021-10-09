package com.santos.jukebox.establishment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.R
import com.santos.jukebox.databinding.ItemSectionMusicBinding
import com.santos.jukebox.establishment.data.MusicEstablishmentResponse
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment

class MusicByTypesAdapter :
    RecyclerView.Adapter<MusicByTypesAdapter.MusicByTypesHolder>() {

    var editListener: ((RegisterMusicEstablishment) -> Unit)? = null
    var deleteListener: ((RegisterMusicEstablishment) -> Unit)? = null
    var listMusic = emptyList<MusicEstablishmentResponse>()
        set(value) {
            field
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicByTypesHolder {
        return MusicByTypesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_section_music, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MusicByTypesHolder, position: Int) {
        holder.bind(listMusic[position])
    }

    override fun getItemCount(): Int = listMusic.count()

    inner class MusicByTypesHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSectionMusicBinding.bind(itemView)

        fun bind(music: MusicEstablishmentResponse) {
            binding.titleTypeMusic.text = music.type
            binding.recyclerCategoryMusic.adapter =
                ItemMusicAdapter(editListener, deleteListener).apply {
                    listNameMusic = music.musics
                }

        }
    }

}
package com.santos.jukebox.establishment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.R
import com.santos.jukebox.databinding.ItemLayoutTypesBinding
import com.santos.jukebox.databinding.ItemTypeMusicBinding

class TypeMusicAdapter(
    var musics: List<String> = listOf()
) :
    RecyclerView.Adapter<TypeMusicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_types, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(musics[position])
    }

    override fun getItemCount() = musics.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ItemLayoutTypesBinding by lazy { ItemLayoutTypesBinding.bind(view) }

        fun bind(music: String) {
            binding.tvType.text = music
        }
    }
}
package com.santos.jukebox.establishment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.R
import com.santos.jukebox.databinding.ItemTypeMusicBinding

class TypeMusicAdapter(
    var musics: List<String> = listOf(),
    var musicsChecked: MutableList<String> = mutableListOf()
) :
    RecyclerView.Adapter<TypeMusicAdapter.ViewHolder>() {

    var onChecked: ((List<String>) -> Unit)? = null
    var onPressedClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_type_music, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(musics[position])
    }

    override fun getItemCount() = musics.size

    fun updateList(allTypeMusics: List<String>, types: List<String>) {
        musics = allTypeMusics
        musicsChecked = types.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ItemTypeMusicBinding by lazy { ItemTypeMusicBinding.bind(view) }

        fun bind(music: String) {
            binding.checkBox.isChecked = musicsChecked.contains(music)
            binding.checkBox.text = music
            binding.checkBox.setOnLongClickListener {
                onPressedClick?.invoke(music)
                false
            }
            binding.checkBox.setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    musicsChecked.add(music)
                } else {
                    musicsChecked.remove(music)
                }
                onChecked?.invoke(musicsChecked)
            }
        }
    }
}
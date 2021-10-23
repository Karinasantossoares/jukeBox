package com.santos.jukebox.establishment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.R
import com.santos.jukebox.databinding.ItemQueueMusicBinding
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment

class ItemQueueMusicAdapter : RecyclerView.Adapter<ItemQueueMusicAdapter.ItemMusicViewHolder>() {

    var tapFinishListener: ((RegisterMusicEstablishment) -> Unit)? = null

    var listNameMusic = emptyList<RegisterMusicEstablishment>()
        set(value) {
            field
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMusicViewHolder {
        return ItemMusicViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_queue_music, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemMusicViewHolder, position: Int) {
        holder.bind(listNameMusic[position])
    }

    override fun getItemCount() = listNameMusic.count()

    inner class ItemMusicViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemQueueMusicBinding.bind(itemView)

        fun bind(music: RegisterMusicEstablishment) {
            binding.btnFinish.setOnClickListener {
                tapFinishListener?.invoke(music)
            }
            binding.tvOrderMusic.text = (adapterPosition + 1).toString()
            binding.tvOrderMusic.text = (adapterPosition + 1).toString()
            binding.tvNameMusic.text = music.title
            binding.tvAuthorMusic.text = music.author
            binding.rvListTypes.adapter = TypeMusicAdapter(music.types)
            music.requestName?.run {
                binding.textViewRequestingPerson.text =
                    binding.root.context.getString(R.string.text_request_person, music.requestName)
            } ?: run {
                binding.textViewRequestingPerson.isVisible = false
            }
        }

    }


}
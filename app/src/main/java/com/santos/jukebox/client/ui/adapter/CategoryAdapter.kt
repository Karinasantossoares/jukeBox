package com.santos.jukebox.client.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santos.jukebox.R
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.ui.adapter.viewholder.CategoryViewHolder

class CategoryAdapter(
    val listener: () -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    var listNameMusic = emptyList<Music>()
        set(value) {
            field
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_music, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(listNameMusic[position])
    }

    override fun getItemCount() = listNameMusic.count()


}
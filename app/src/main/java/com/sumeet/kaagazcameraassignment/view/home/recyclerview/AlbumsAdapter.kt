package com.sumeet.kaagazcameraassignment.view.home.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumeet.kaagazcameraassignment.R
import com.sumeet.kaagazcameraassignment.data.AlbumEntity

class AlbumsAdapter(
    private var list : List<AlbumEntity>,
    private val listener: AlbumItemClickListener,
) : RecyclerView.Adapter<AlbumsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.album_item_layout_list,
            parent,
            false)
        return AlbumsViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        return holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
package com.sumeet.kaagazcameraassignment.view.home.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import kotlinx.android.synthetic.main.album_item_layout_list.view.*

class AlbumsViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    fun setData(albumEntity: AlbumEntity) {
        itemView.tvAlbumName.text = albumEntity.name
        itemView.tvAlbumCreationDate.text = albumEntity.timestamp
        //Glide.with(itemView).load()
    }
}
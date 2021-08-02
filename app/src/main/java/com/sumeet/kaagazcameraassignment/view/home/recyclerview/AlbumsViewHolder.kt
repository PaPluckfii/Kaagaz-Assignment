package com.sumeet.kaagazcameraassignment.view.home.recyclerview

import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.sumeet.kaagazcameraassignment.R
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import kotlinx.android.synthetic.main.album_item_layout_list.view.*

class AlbumsViewHolder(
    view : View,
    private val listener: AlbumItemClickListener
) : RecyclerView.ViewHolder(view){

    fun setData(albumEntity: AlbumEntity) {
        itemView.tvAlbumName.text = albumEntity.name
        itemView.tvAlbumCreationDate.text = TimeAgo.from(albumEntity.timestamp)
            Glide.with(itemView).load(R.drawable.nothing_here_dog).into(itemView.ivAlbumThumbnail)

        itemView.setOnClickListener{
            listener.onItemClicked(albumEntity)
        }
    }

}
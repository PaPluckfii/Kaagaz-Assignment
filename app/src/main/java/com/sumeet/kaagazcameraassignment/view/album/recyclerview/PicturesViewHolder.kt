package com.sumeet.kaagazcameraassignment.view.album.recyclerview

import android.net.Uri
import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import kotlinx.android.synthetic.main.picture_item_layout_list.view.*

class PicturesViewHolder(
    view : View,
    private  val listener : PictureItemClickListener
) : RecyclerView.ViewHolder(view){

    fun setData(pictureEntity: PictureEntity){
        Glide.with(itemView).load(pictureEntity.uri?.toUri()).into(itemView.imageViewCard)
    }

}
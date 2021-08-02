package com.sumeet.kaagazcameraassignment.view.album.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumeet.kaagazcameraassignment.R
import com.sumeet.kaagazcameraassignment.data.PictureEntity

class PicturesAdapter(
    private var list : List<PictureEntity>,
    private val listener: PictureItemClickListener
) : RecyclerView.Adapter<PicturesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicturesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.picture_item_layout_list,
            parent,
            false
        )
        return PicturesViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: PicturesViewHolder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
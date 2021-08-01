package com.sumeet.kaagazcameraassignment.view.album.recyclerview

import com.sumeet.kaagazcameraassignment.data.PictureEntity

interface PictureItemClickListener {
    fun onItemClicked(pictureEntity: PictureEntity)
}
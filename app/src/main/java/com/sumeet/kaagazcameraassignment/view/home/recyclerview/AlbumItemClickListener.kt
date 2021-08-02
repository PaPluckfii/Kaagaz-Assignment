package com.sumeet.kaagazcameraassignment.view.home.recyclerview

import com.sumeet.kaagazcameraassignment.data.AlbumEntity

interface AlbumItemClickListener {
    fun onItemClicked(albumEntity: AlbumEntity)
}
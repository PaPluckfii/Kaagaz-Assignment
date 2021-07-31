package com.sumeet.kaagazcameraassignment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.sumeet.kaagazcameraassignment.data.AlbumDao
import com.sumeet.kaagazcameraassignment.data.AlbumDatabase
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.data.PictureEntity

class AlbumRepository(context: Context) {

    private val albumDao = AlbumDatabase.getInstance(context).albumDao()

    suspend fun getAllAlbums() : List<AlbumEntity>{
        return albumDao.getAll()
    }

    suspend fun getAllPictures(albumId : Int) : List<PictureEntity>{
        return albumDao.getPicturesForAlbum(albumId)
    }

    suspend fun insertAlbum(albumEntity: AlbumEntity){
        albumDao.insertAlbum(albumEntity)
    }

    suspend fun insertPictures(pictureEntity: PictureEntity){
        albumDao.insertPicture(pictureEntity)
    }

}
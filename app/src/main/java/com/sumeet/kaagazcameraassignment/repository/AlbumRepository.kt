package com.sumeet.kaagazcameraassignment.repository

import androidx.lifecycle.LiveData
import com.sumeet.kaagazcameraassignment.data.AlbumDao
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.data.PictureEntity

class AlbumRepository(private val albumDao: AlbumDao) {

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
package com.sumeet.kaagazcameraassignment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sumeet.kaagazcameraassignment.data.*
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val albumDao: AlbumDao,
    private val pictureDao: PictureDao
) {

    fun getAllAlbums() : LiveData<List<AlbumEntity>> {
        return albumDao.getAll()
    }

    fun getAllPictures(albumName : String) : LiveData<List<PictureEntity>> {
        return pictureDao.getPicturesForAlbum(albumName)
    }

    suspend fun insertAlbum(albumEntity: AlbumEntity){
        albumDao.insertAlbum(albumEntity)
    }

    suspend fun insertPictures(pictureEntity: PictureEntity){
        pictureDao.insertPicture(pictureEntity)
    }

    suspend fun getListOfPictures(albumName : String): List<PictureEntity> {
        return pictureDao.getListOfPictures(albumName)
    }

}
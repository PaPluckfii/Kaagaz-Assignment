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

    fun deleteImage(pictureEntity: PictureEntity) {
        pictureDao.deleteImage(pictureEntity)
    }

    fun deleteAlbum(currentAlbum: AlbumEntity) {
        albumDao.deleteAlbum(currentAlbum)
    }

    fun deleteImages(name: String?) {
        pictureDao.deleteImages(name)
    }

    fun getListOfPics(name: String?): List<PictureEntity> {
        return pictureDao.getListOfPics(name)
    }

    suspend fun getThumbnail(albumEntity: AlbumEntity) : List<String>{
        return pictureDao.getThumbnail(albumEntity.name)
    }

}
package com.sumeet.kaagazcameraassignment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sumeet.kaagazcameraassignment.data.AlbumDao
import com.sumeet.kaagazcameraassignment.data.AlbumDatabase
import com.sumeet.kaagazcameraassignment.data.AlbumEntity
import com.sumeet.kaagazcameraassignment.data.PictureEntity
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val context: Context) {

    private val albumDao = AlbumDatabase.getInstance(context).albumDao()

    fun getAllAlbums() : LiveData<List<AlbumEntity>> {
        return albumDao.getAll()
    }

    fun getAllPictures(albumId : Int) : LiveData<List<PictureEntity>> {
        return albumDao.getPicturesForAlbum(albumId)
    }

    suspend fun insertAlbum(albumEntity: AlbumEntity){
        albumDao.insertAlbum(albumEntity)
    }

    suspend fun insertPictures(pictureEntity: PictureEntity){
        albumDao.insertPicture(pictureEntity)
    }

}
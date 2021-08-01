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

    suspend fun getAllAlbums() : MutableLiveData<List<AlbumEntity>>{
        return albumDao.getAll()
    }

    suspend fun getAllPictures(albumId : Int) : MutableLiveData<List<PictureEntity>>{
        return albumDao.getPicturesForAlbum(albumId)
    }

    suspend fun insertAlbum(albumEntity: AlbumEntity){
        albumDao.insertAlbum(albumEntity)
    }

    suspend fun insertPictures(pictureEntity: PictureEntity){
        albumDao.insertPicture(pictureEntity)
    }

}
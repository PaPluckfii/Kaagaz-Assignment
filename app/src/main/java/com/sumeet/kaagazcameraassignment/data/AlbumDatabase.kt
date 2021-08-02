package com.sumeet.kaagazcameraassignment.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AlbumEntity::class,PictureEntity::class], version = 1)
abstract class AlbumDatabase : RoomDatabase(){
    abstract fun albumDao() : AlbumDao
    abstract fun pictureDao() : PictureDao
}
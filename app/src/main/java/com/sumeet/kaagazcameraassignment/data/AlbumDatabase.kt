package com.sumeet.kaagazcameraassignment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlbumEntity::class,PictureEntity::class], version = 6)
abstract class AlbumDatabase : RoomDatabase(){
    abstract fun albumDao() : AlbumDao
    abstract fun pictureDao() : PictureDao
}
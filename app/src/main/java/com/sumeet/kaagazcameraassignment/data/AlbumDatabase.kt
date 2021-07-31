package com.sumeet.kaagazcameraassignment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlbumEntity::class,PictureEntity::class], version = 1)
abstract class AlbumDatabase : RoomDatabase(){

    abstract fun albumDao() : AlbumDao

    companion object {
        private var INSTANCE: AlbumDatabase? = null
        private const val DATABASE_NAME = "album_database"

        fun getInstance(context: Context): AlbumDatabase {
            return if (INSTANCE == null) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumDatabase::class.java,
                    DATABASE_NAME
                )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                INSTANCE!!
            } else {
                INSTANCE!!
            }
        }
    }

}
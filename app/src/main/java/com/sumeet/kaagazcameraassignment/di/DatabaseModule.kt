package com.sumeet.kaagazcameraassignment.di

import android.content.Context
import com.sumeet.kaagazcameraassignment.data.AlbumDao
import com.sumeet.kaagazcameraassignment.data.AlbumDatabase
import com.sumeet.kaagazcameraassignment.data.PictureDao
import com.sumeet.kaagazcameraassignment.repository.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAlbumDao(albumDatabase: AlbumDatabase) : AlbumDao {
        return albumDatabase.albumDao()
    }

    @Singleton
    @Provides
    fun providePictureDao(albumDatabase: AlbumDatabase) : PictureDao {
        return albumDatabase.pictureDao()
    }

}
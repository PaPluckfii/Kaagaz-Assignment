package com.sumeet.kaagazcameraassignment.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sumeet.kaagazcameraassignment.data.AlbumDao
import com.sumeet.kaagazcameraassignment.data.AlbumDatabase
import com.sumeet.kaagazcameraassignment.data.PictureDao
import com.sumeet.kaagazcameraassignment.repository.AlbumRepository
import com.sumeet.kaagazcameraassignment.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Singleton
    @Provides
    fun provideContext(application: Application) : Context = application.applicationContext

    @Singleton
    @Provides
    fun provideAlbumDatabase(context: Context) : AlbumDatabase {
        return Room.databaseBuilder(
            context,
            AlbumDatabase::class.java,
            "album_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideAlbumRepository(
        albumDao: AlbumDao,
        pictureDao: PictureDao
    ) : AlbumRepository = AlbumRepository(albumDao,pictureDao)

    @Singleton
    @Provides
    fun provideDispatchers() : DispatcherProvider = object : DispatcherProvider{
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

}
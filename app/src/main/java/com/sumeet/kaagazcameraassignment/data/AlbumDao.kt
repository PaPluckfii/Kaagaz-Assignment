package com.sumeet.kaagazcameraassignment.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(albumEntity: AlbumEntity)

    @Query("SELECT * FROM album_table")
    suspend fun getAll() : List<AlbumEntity>

}
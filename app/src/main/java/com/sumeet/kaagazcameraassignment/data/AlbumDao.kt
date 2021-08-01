package com.sumeet.kaagazcameraassignment.data


import androidx.room.*

@Dao
interface AlbumDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(albumEntity: AlbumEntity)

    @Query("SELECT * FROM album_table")
    suspend fun getAll() : List<AlbumEntity>

}
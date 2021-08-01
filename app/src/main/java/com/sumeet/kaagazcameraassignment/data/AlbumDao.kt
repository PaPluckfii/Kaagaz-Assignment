package com.sumeet.kaagazcameraassignment.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(albumEntity: AlbumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(pictureEntity: PictureEntity)

    @Query("SELECT * FROM album_table")
    fun getAll() : LiveData<List<AlbumEntity>>

    @Query("SELECT * FROM picture_table WHERE album_id = :albumId")
    fun getPicturesForAlbum(albumId : Int) : LiveData<List<PictureEntity>>

}
package com.sumeet.kaagazcameraassignment.data

import androidx.room.*

@Dao
interface AlbumDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(albumEntity: AlbumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(pictureEntity: PictureEntity)

    @Query("SELECT * FROM album_table")
    suspend fun getAll() : List<AlbumEntity>

    @Query("SELECT * FROM picture_table WHERE album_id = :albumId")
    suspend fun getPicturesForAlbum(albumId : Int) : List<PictureEntity>

}
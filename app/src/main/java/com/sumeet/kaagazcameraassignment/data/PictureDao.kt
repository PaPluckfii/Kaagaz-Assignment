package com.sumeet.kaagazcameraassignment.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(pictureEntity: PictureEntity)

    @Query("SELECT * FROM picture_table WHERE album_name = :albumName")
    fun getPicturesForAlbum(albumName : String) : LiveData<List<PictureEntity>>

    @Query("SELECT * FROM picture_table WHERE album_name = :albumName")
    suspend fun getListOfPictures(albumName : String) : List<PictureEntity>

}
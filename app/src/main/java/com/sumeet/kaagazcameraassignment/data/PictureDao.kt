package com.sumeet.kaagazcameraassignment.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(pictureEntity: PictureEntity)

    @Query("SELECT * FROM picture_table WHERE album_name = :albumName")
    suspend fun getPicturesForAlbum(albumName : String) : List<PictureEntity>

}
package com.sumeet.kaagazcameraassignment.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(pictureEntity: PictureEntity)

    @Query("SELECT * FROM picture_table WHERE album_name = :albumName")
    fun getPicturesForAlbum(albumName : String) : LiveData<List<PictureEntity>>

    @Delete
    fun deleteImage(pictureEntity: PictureEntity)

    @Query("DELETE FROM picture_table WHERE album_name = :name")
    fun deleteImages(name: String?)

    @Query("SELECT * FROM picture_table WHERE album_name = :name")
    fun getListOfPics(name: String?) : List<PictureEntity>

    @Query("SELECT uri FROM picture_table WHERE album_name = :name")
    suspend fun getThumbnail(name: String?) : List<String>

}
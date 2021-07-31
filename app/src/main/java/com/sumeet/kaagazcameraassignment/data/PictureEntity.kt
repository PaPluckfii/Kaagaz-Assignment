package com.sumeet.kaagazcameraassignment.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture_table")
data class PictureEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pic_id") val picId : Int,
    @ColumnInfo(name = "album_id") val albumId : Int,
    @ColumnInfo(name = "pic_name") var picName : String,
    @ColumnInfo(name = "pic_address") var picAddress : String
)

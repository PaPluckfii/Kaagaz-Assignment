package com.sumeet.kaagazcameraassignment.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "picture_table")
data class PictureEntity(
    @ColumnInfo(name = "album_id") val albumId : Int?,
    @ColumnInfo(name = "pic_name") var picName : String?,
    @ColumnInfo(name = "uri") var uri : String?
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var picId : Int = 0
}

package com.sumeet.kaagazcameraassignment.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "album_table")
data class AlbumEntity(
    @ColumnInfo(name = "album_name") var name : String?,
    @ColumnInfo(name = "timestamp") var timestamp : String?,
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
package com.sumeet.kaagazcameraassignment.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "album_name") var name : String,
    @ColumnInfo(name = "timestamp") var timestamp : String,
    @ColumnInfo(name = "address") var address: String
)
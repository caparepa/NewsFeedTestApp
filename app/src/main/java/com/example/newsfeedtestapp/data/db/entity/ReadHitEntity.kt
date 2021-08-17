package com.example.newsfeedtestapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "read_hits")
data class ReadHitEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo(name = "object_id")
    val objectId: String?
)

package com.example.newsfeedtestapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "read_hits",
    indices =
    [Index(
        value = ["story_id"],
        unique = true
    )]
)
data class ReadHitEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @ColumnInfo(name = "story_id")
    val storyId: Int?
)

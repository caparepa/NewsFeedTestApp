package com.example.newsfeedtestapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "read_hits",
    indices =
    [Index(
        value = ["story_url"],
        unique = true
    )]
)
data class ReadHitEntity(
    @ColumnInfo(name = "story_url")
    val storyUrl: String
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

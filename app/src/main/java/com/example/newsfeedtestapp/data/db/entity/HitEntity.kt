package com.example.newsfeedtestapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.newsfeedtestapp.data.model.HighlightResult

@Entity(tableName = "hits")
data class HitEntity(
    @ColumnInfo(name = "created_at")
    val createdAt: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "points")
    val points: Int?,
    @ColumnInfo(name = "story_text")
    val storyText: String?,
    @ColumnInfo(name = "comment_text")
    val commentText: String?,
    @ColumnInfo(name = "num_comments")
    val numComments: Int?,
    @ColumnInfo(name = "story_id")
    val storyId: Int?,
    @ColumnInfo(name = "story_title")
    val storyTitle: String?,
    @ColumnInfo(name = "story_url")
    val storyUrl: String?,
    @ColumnInfo(name = "parent_id")
    val parentId: Int?,
    @ColumnInfo(name = "created_at_i")
    val createdAtI: Int?,
    @ColumnInfo(name = "_tags")
    val tags: List<String>?,
    @ColumnInfo(name = "object_id")
    val objectID: Long?,
    @ColumnInfo(name = "_highlightResult")
    val highlightResult: HighlightResult?
)
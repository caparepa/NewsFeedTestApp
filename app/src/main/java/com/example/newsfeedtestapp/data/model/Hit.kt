package com.example.newsfeedtestapp.data.model

import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("title")
    val title: Any?,
    @SerializedName("url")
    val url: Any?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("points")
    val points: Any?,
    @SerializedName("story_text")
    val storyText: Any?,
    @SerializedName("comment_text")
    val commentText: String?,
    @SerializedName("num_comments")
    val numComments: Any?,
    @SerializedName("story_id")
    val storyId: Any?,
    @SerializedName("story_title")
    val storyTitle: Any?,
    @SerializedName("story_url")
    val storyUrl: Any?,
    @SerializedName("parent_id")
    val parentId: Int?,
    @SerializedName("created_at_i")
    val createdAtI: Int?,
    @SerializedName("_tags")
    val tags: List<String>?,
    @SerializedName("objectID")
    val objectID: String?,
    @SerializedName("_highlightResult")
    val highlightResult: HighlightResult?
)
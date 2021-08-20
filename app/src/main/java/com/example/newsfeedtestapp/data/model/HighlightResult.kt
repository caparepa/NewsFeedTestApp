package com.example.newsfeedtestapp.data.model

import com.google.gson.annotations.SerializedName

data class HighlightResult(
    @SerializedName("author")
    val author: Author?,
    @SerializedName("comment_text")
    val commentText: CommentText?,
    @SerializedName("story_title")
    val storyTitle: StoryTitle?,
    @SerializedName("story_url")
    val storyUrl: StoryUrl?,
    @SerializedName("title")
    val title: Title?,
    @SerializedName("url")
    val url: Url?,
    @SerializedName("story_text")
    val storyText: StoryText?
)
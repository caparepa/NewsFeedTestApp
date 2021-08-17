package com.example.newsfeedtestapp.data.model

import com.google.gson.annotations.SerializedName

data class StoryTitle(
    @SerializedName("value")
    val value: String?,
    @SerializedName("matchLevel")
    val matchLevel: String?,
    @SerializedName("matchedWords")
    val matchedWords: List<String>?,
    @SerializedName("fullyHighlighted")
    val fullyHighlighted: Boolean?
)
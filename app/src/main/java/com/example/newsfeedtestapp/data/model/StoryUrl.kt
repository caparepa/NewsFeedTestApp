package com.example.newsfeedtestapp.data.model

import com.google.gson.annotations.SerializedName

data class StoryUrl(
    @SerializedName("value")
    val value: String?,
    @SerializedName("matchLevel")
    val matchLevel: String?,
    @SerializedName("fullyHighlighted")
    val fullyHighlighted: Boolean?,
    @SerializedName("matchedWords")
    val matchedWords: List<String>?
)
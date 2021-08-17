package com.example.newsfeedtestapp.repository

import com.example.newsfeedtestapp.data.model.Hit

interface NewsFeedRepository {
    suspend fun getNewsFeedData(): List<Hit>?
    suspend fun fetchNewsFeedData()
    suspend fun persistNewsFeedData()
}
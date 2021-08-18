package com.example.newsfeedtestapp.repository

import com.example.newsfeedtestapp.data.model.Hit

interface NewsFeedRepository {
    suspend fun getNewsFeedData(): List<Hit>?
    suspend fun fetchNewsFeedData(): List<Hit>?
    suspend fun persistNewsFeedData(hitLlist: List<Hit>?)
}
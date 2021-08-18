package com.example.newsfeedtestapp.repository

import com.example.newsfeedtestapp.data.model.Hit
import com.example.newsfeedtestapp.network.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class NewsFeedRepositoryImpl : NewsFeedRepository, KoinComponent {

    private val api = ApiClient.invoke()

    override suspend fun getNewsFeedData(): List<Hit>? =
        withContext(Dispatchers.IO) {
            val response = api.getNewsFeed()
            val hitList = response.body()?.hits
            val filteredList = hitList.filterStories()
            filteredList
        }

    override suspend fun fetchNewsFeedData(): List<Hit>? {
        TODO("Not yet implemented")
    }

    override suspend fun persistNewsFeedData(hitLlist: List<Hit>?) {
        TODO("Not yet implemented")
    }

    /**
     * Filter out comments from the actual articles
     */
    private fun List<Hit>?.filterStories(): List<Hit>? {
        return this?.filter { it.storyId != null }
    }
}
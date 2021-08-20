package com.example.newsfeedtestapp.repository

import com.example.newsfeedtestapp.data.db.dao.HitDao
import com.example.newsfeedtestapp.data.db.dao.ReadHitDao
import com.example.newsfeedtestapp.data.db.entity.HitEntity
import com.example.newsfeedtestapp.data.db.entity.ReadHitEntity
import com.example.newsfeedtestapp.data.model.Hit
import com.example.newsfeedtestapp.network.api.ApiClient
import com.example.newsfeedtestapp.utils.mapTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class NewsFeedRepositoryImpl(
    val hitDao: HitDao,
    val readHitDao: ReadHitDao
) : NewsFeedRepository, KoinComponent {

    private val api = ApiClient.invoke()

    override suspend fun getNewsFeedData(): List<Hit>? =
        withContext(Dispatchers.IO) {
            val response = api.getNewsFeed()
            val hitList = response.body()?.hits
            val filteredList = hitList.filterStories()
            persistNewsFeedData(filteredList)
            filteredList
        }

    override suspend fun fetchNewsFeedData(): List<Hit>? {
        var result = arrayListOf<Hit>()
        val readList = readHitDao.fetchReadHits()
        val hitList = if(readList.isEmpty()) {
            hitDao.fetchNewsHits()
        }else {
            hitDao.fetchUnreadNewsHits(readList.getIdList())
        }
        hitList.forEach { hit ->
            val model = Hit(
                createdAt = hit.createdAt,
                title = hit.title,
                url = hit.url,
                author = hit.author,
                points = hit.points,
                storyText = hit.storyText,
                commentText = hit.commentText,
                numComments = hit.numComments,
                storyId = hit.storyId,
                storyTitle = hit.storyTitle,
                storyUrl = hit.storyUrl,
                parentId = hit.parentId,
                createdAtI = hit.createdAtI,
                tags = hit.tags,
                objectID = hit.objectID,
                highlightResult = hit.highlightResult
            )
            result.add(model)
        }
        return result
    }

    override suspend fun persistNewsFeedData(hitList: List<Hit>?) {
        hitList?.forEach { hit ->
            val entity = HitEntity(
                createdAt = hit.createdAt,
                title = hit.title,
                url = hit.url,
                author = hit.author,
                points = hit.points,
                storyText = hit.storyText,
                commentText = hit.commentText,
                numComments = hit.numComments,
                storyId = hit.storyId,
                storyTitle = hit.storyTitle,
                storyUrl = hit.storyUrl,
                parentId = hit.parentId,
                createdAtI = hit.createdAtI,
                tags = hit.tags,
                objectID = hit.objectID,
                highlightResult = hit.highlightResult
            )
            hitDao.upsert(entity)
        }
    }

    /**
     * Filter out comments from the actual articles
     */
    private fun List<Hit>?.filterStories(): List<Hit>? {
        return this?.filter { it.storyId != null }
    }

    private fun List<ReadHitEntity>?.getIdList(): List<Int> {

        var result = arrayListOf<Int>()

        if(this.isNullOrEmpty())
            return result

        this.forEach { read ->
            result.add(read.storyId!!)
        }

        return result
    }
}
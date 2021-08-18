package com.example.newsfeedtestapp.network.api

import com.example.newsfeedtestapp.data.model.NewsFeedResponse
import retrofit2.Response
import retrofit2.http.GET

interface Endpoints {

    @GET("/api/v1/search_by_date?query=mobile")
    suspend fun getNewsFeed(): Response<NewsFeedResponse?>

}
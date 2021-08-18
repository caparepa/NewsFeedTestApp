package com.example.newsfeedtestapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.newsfeedtestapp.data.model.Hit
import com.example.newsfeedtestapp.repository.NewsFeedRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NewsFeedViewModel(context: Context): BaseViewModel(), KoinComponent {

    private val newsFeedRepository: NewsFeedRepository by inject()

    val newsList = MutableLiveData<List<Hit>?>()

    fun getNewsFeedList() {

    }

    fun fetchNewsFeedList() {

    }

    private suspend fun getNewsFeedListAsync() {

    }

    private suspend fun fetchNewsFeedListAsync() {

    }

}
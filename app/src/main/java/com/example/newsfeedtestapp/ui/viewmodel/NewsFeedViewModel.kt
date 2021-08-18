package com.example.newsfeedtestapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsfeedtestapp.data.model.Hit
import com.example.newsfeedtestapp.repository.NewsFeedRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NewsFeedViewModel(context: Context): BaseViewModel(), KoinComponent {

    private val newsFeedRepository: NewsFeedRepository by inject()

    val newsList = MutableLiveData<List<Hit>?>()

    fun getNewsFeedList() {
        viewModelScope.launch {
            getNewsFeedListAsync()
        }
    }

    fun fetchNewsFeedList() {

    }

    private suspend fun getNewsFeedListAsync() {
        val result = kotlin.runCatching {
            showLoading()
            newsFeedRepository.getNewsFeedData()
        }
        with(result) {
            dismissLoading()
            onSuccess {
                newsList.postValue(it)
            }
            onFailure {
                onError.postValue(it.message)
            }
        }
    }

    private suspend fun fetchNewsFeedListAsync() {

    }

}
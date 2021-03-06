package com.example.newsfeedtestapp.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsfeedtestapp.data.model.Hit
import com.example.newsfeedtestapp.repository.NewsFeedRepository
import com.example.newsfeedtestapp.utils.NoConnectivityException
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.net.UnknownHostException

class NewsFeedViewModel(val context: Context): BaseViewModel(), KoinComponent {

    private val newsFeedRepository: NewsFeedRepository by inject()

    val newsList = MutableLiveData<List<Hit>?>()
    val itemDeleted = MutableLiveData<String>()

    fun getNewsFeedList() {
        viewModelScope.launch {
            getNewsFeedListAsync()
        }
    }

    fun fetchNewsFeedList() {
        viewModelScope.launch {
            fetchNewsFeedListAsync()
        }
    }

    fun deleteEntryFromNewsFeed(hit: Hit) {
        viewModelScope.launch {
            deleteEntryFromNewsFeedAsync(hit)
        }
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
                val message = it.message
                when (it) {
                    is NoConnectivityException -> {
                        onConnError.postValue("No internet connection. " +
                                "Will try to load data from local storage.")
                    }
                    is UnknownHostException -> {
                        onConnError.postValue("Host not responding. " +
                                "Will try to load data from local storage.")
                    }
                    else -> { onError.postValue(message) }
                }
            }
        }
    }

    private suspend fun fetchNewsFeedListAsync() {
        val result = kotlin.runCatching {
            showLoading()
            newsFeedRepository.fetchNewsFeedData()
        }
        with(result) {
            dismissLoading()
            onSuccess {
                newsList.postValue(it)
            }
            onFailure {
                val message = it.message
                onError.postValue(message)
            }
        }
    }

    private suspend fun deleteEntryFromNewsFeedAsync(hit: Hit) {
        val result = kotlin.runCatching {
            newsFeedRepository.deleteEntryFromNewsFeed(hit)
        }
        with(result) {
            onSuccess {
                itemDeleted.postValue("Item deleted successfully")
            }
            onFailure {
                onError.postValue("Couldn't delete entry: ${it.message}")
            }
        }
    }

}
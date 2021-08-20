package com.example.newsfeedtestapp.ui.viewmodel

import android.content.Context
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
                onError.postValue(message)
                when (it) {
                    is NoConnectivityException -> {
                        onConnError.postValue(message)
                    }
                    is UnknownHostException -> {
                        onConnError.postValue(message)
                    }
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

}
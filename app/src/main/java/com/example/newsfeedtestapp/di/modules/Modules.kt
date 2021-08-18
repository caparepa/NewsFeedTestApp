package com.example.newsfeedtestapp.di.modules

import android.content.Context
import com.example.newsfeedtestapp.network.api.ApiClient
import com.example.newsfeedtestapp.network.interceptor.ConnectivityInterceptor
import com.example.newsfeedtestapp.network.interceptor.ConnectivityInterceptorImpl
import com.example.newsfeedtestapp.repository.NewsFeedRepository
import com.example.newsfeedtestapp.repository.NewsFeedRepositoryImpl
import com.example.newsfeedtestapp.ui.viewmodel.NewsFeedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val networkModule = module {
    single { ApiClient.invoke() }
    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(get()) }
}

val repositoryModule = module {
    factory<NewsFeedRepository> { NewsFeedRepositoryImpl() }
}

val localModule = module {

}

val dataModule = module {
    single { androidContext().getSharedPreferences("Prefs", Context.MODE_PRIVATE) }
}

val databaseModule = module {

}

val viewModelModule = module {
    viewModel { NewsFeedViewModel(get()) }
}
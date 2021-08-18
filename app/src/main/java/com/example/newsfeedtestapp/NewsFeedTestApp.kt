package com.example.newsfeedtestapp

import android.app.Application
import com.example.newsfeedtestapp.di.CoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class NewsFeedTestApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        CoreModule.init()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@NewsFeedTestApp)
        }
    }
}
package com.example.newsfeedtestapp.di

import com.example.newsfeedtestapp.di.modules.*
import org.koin.core.context.loadKoinModules

object CoreModule {
    private val modules = listOf(
        networkModule,
        repositoryModule,
        localModule,
        dataModule,
        databaseModule,
        viewModelModule
    )

    fun init() = loadKoinModules(modules)
}
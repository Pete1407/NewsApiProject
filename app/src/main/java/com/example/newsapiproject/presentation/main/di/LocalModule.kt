package com.example.newsapiproject.presentation.main.di

import com.example.newsapiproject.data.database.NewsDao
import com.example.newsapiproject.data.repository.data_source.LocalDataSource
import com.example.newsapiproject.data.repository.data_source_lmpl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(newsDao: NewsDao): LocalDataSource {
        return LocalDataSourceImpl(newsDao)
    }
}
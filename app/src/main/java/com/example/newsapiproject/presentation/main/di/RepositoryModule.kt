package com.example.newsapiproject.presentation.main.di

import com.example.newsapiproject.data.repository.NewsRepositoryImpl
import com.example.newsapiproject.data.repository.data_source.RemoteDataSource
import com.example.newsapiproject.data.repository.data_source_lmpl.RemoteDataSourceImpl
import com.example.newsapiproject.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(remoteDataSource: RemoteDataSource): NewsRepository{
        return NewsRepositoryImpl(remoteDataSource)
    }
}
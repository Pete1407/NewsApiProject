package com.example.newsapiproject.presentation.main.di

import com.example.newsapiproject.data.remote.ApiService
import com.example.newsapiproject.data.repository.data_source.RemoteDataSource
import com.example.newsapiproject.data.repository.data_source_lmpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService):RemoteDataSource{
        return RemoteDataSourceImpl(apiService)
    }
}
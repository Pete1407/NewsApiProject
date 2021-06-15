package com.example.newsapiproject.presentation.main.di

import com.example.newsapiproject.domain.repository.NewsRepository
import com.example.newsapiproject.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetNewsUseCase(repository: NewsRepository): GetNewsUsecase{
        return GetNewsUsecase(repository)
    }

    @Provides
    fun provideDeleteNewsUseCase(repository: NewsRepository):DeleteNewsUsecase{
        return DeleteNewsUsecase(repository)
    }

    @Provides
    fun provideGetSaveNewsUseCase(repository: NewsRepository):GetSaveNewsUsecase{
        return GetSaveNewsUsecase(repository)
    }

    @Provides
    fun provideGetSearchNewsUseCase(repository: NewsRepository):GetSearchNewsUsecase{
        return GetSearchNewsUsecase(repository)
    }

    @Provides
    fun provideSaveNewsUseCase(repository: NewsRepository):SaveNewsUsecase{
        return SaveNewsUsecase(repository)
    }
}
package com.example.newsapiproject.presentation.main.di

import com.example.newsapiproject.domain.usecase.*
import com.example.newsapiproject.presentation.main.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideMainViewModelFactory(
        getNewsUsecase: GetNewsUsecase,
        searchNewsUsecase: GetSearchNewsUsecase,
        saveNewsUsecase: SaveNewsUsecase,
        getSaveNewsUsecase: GetSaveNewsUsecase,
        deleteNewsUsecase: DeleteNewsUsecase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getNewsUsecase,
            searchNewsUsecase,
            saveNewsUsecase,
            getSaveNewsUsecase,
            deleteNewsUsecase
        )
    }
}
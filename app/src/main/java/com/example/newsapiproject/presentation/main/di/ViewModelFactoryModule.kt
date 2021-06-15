package com.example.newsapiproject.presentation.main.di

import com.example.newsapiproject.domain.usecase.GetNewsUsecase
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
    fun provideMainViewModelFactory(getNewsUsecase: GetNewsUsecase):MainViewModelFactory{
        return MainViewModelFactory(getNewsUsecase)
    }
}
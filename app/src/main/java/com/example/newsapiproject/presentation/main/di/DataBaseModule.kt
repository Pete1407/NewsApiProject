package com.example.newsapiproject.presentation.main.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapiproject.data.database.NewsDao
import com.example.newsapiproject.data.database.NewsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideRoomDataBase(application: Application): RoomDatabase {
        return Room.databaseBuilder(application, NewsDataBase::class.java, "article_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(newsDataBase: NewsDataBase):NewsDao{
        return newsDataBase.getDao()
    }
}
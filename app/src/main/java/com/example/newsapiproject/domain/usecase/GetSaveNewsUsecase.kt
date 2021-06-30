package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSaveNewsUsecase(private val newsRepository: NewsRepository) {

    suspend fun execute():Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }
}
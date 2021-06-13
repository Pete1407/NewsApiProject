package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.domain.repository.NewsRepository

class DeleteNewsUsecase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article){
        newsRepository.deleteNew(article)
    }
}
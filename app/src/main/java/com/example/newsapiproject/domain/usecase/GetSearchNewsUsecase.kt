package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.data.model.NewsResponse
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.repository.NewsRepository
import retrofit2.Response

class GetSearchNewsUsecase(private val newsRepository: NewsRepository) {

    suspend fun execute(queryString: String): Resource<NewsResponse> {
        return newsRepository.getSearchedNews(queryString)
    }
}
package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.data.model.NewsResponse
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.repository.NewsRepository

class GetNewsUsecase(private val repository: NewsRepository) {

    suspend fun execute(country : String,page : Int):Resource<NewsResponse>{
        return repository.getNewsHeadLines(country, page)
    }
}
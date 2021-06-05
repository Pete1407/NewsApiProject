package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.domain.repository.NewsRepository

class GetSearchNewsUsecase(private val newsRepository: NewsRepository) {
}
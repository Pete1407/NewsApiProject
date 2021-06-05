package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.domain.repository.NewsRepository

class GetNewsUsecase(private val repository: NewsRepository) {
}
package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.domain.repository.NewsRepository

class DeleteNewsUsecase(private val newsRepository: NewsRepository) {
}
package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.domain.repository.NewsRepository

class GetSaveNewsUsecase(private val newsRepository: NewsRepository) {
}
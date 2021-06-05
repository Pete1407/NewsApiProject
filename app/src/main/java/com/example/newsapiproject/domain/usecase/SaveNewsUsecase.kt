package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.domain.repository.NewsRepository

class SaveNewsUsecase(private val newsRepository: NewsRepository) {
}
package com.example.newsapiproject.domain.repository

import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.model.NewsResponse
import com.example.newsapiproject.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRepository {

    suspend fun getNewsHeadLines():Resource<NewsResponse>

    suspend fun getSearchedNews(searchQuery : String) : Resource<NewsResponse>

    suspend fun saveNew(article: Article)

    suspend fun deleteNew(article : Article)

    fun getSavedNews():Flow<List<Article>>
}
package com.example.newsapiproject.domain.repository

import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.model.NewsResponse
import com.example.newsapiproject.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

// collect feature function
interface NewsRepository {

    suspend fun getNewsHeadLines(country : String,page : Int):Resource<NewsResponse>

    suspend fun getSearchedNews(country : String,searchQuery : String) : Resource<NewsResponse>

    suspend fun saveNew(article: Article)

    suspend fun deleteNew(article : Article)

    suspend fun getSavedNews():Flow<List<Article>>
}
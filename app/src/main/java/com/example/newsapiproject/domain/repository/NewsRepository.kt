package com.example.newsapiproject.domain.repository

import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.model.NewsResponse
import com.example.newsapiproject.data.util.Resource
import retrofit2.Response

interface NewsRepository {

    suspend fun getNewsHeadLines():Resource<NewsResponse>

    suspend fun saveNews(item : Article):Article

    //suspend fun deleteNews(item : Ar)
}
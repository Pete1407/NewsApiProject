package com.example.newsapiproject.data.repository.data_source

import com.example.newsapiproject.data.model.NewsResponse
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getNewsHeadLines(): Response<NewsResponse>
}
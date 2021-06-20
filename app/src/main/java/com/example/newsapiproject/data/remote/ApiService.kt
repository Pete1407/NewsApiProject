package com.example.newsapiproject.data.remote

import com.example.newsapiproject.BuildConfig
import com.example.newsapiproject.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getNewsHeadLines(
        @Query("country") country : String,
        @Query("page") page : Int,
        @Query("apiKey") apiKey : String = BuildConfig.API_KEY
    ): Response<NewsResponse>
}
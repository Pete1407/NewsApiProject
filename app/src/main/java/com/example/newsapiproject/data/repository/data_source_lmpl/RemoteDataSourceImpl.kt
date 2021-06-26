package com.example.newsapiproject.data.repository.data_source_lmpl

import com.example.newsapiproject.data.model.NewsResponse
import com.example.newsapiproject.data.remote.ApiService
import com.example.newsapiproject.data.repository.data_source.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(
    private val service: ApiService
) : RemoteDataSource {

    override suspend fun getNewsHeadLines(
        country: String,
        page: Int
    ): Response<NewsResponse> {
        return service.getNewsHeadLines(country, page)
    }

    override suspend fun searchNewsHeadLines(
        country: String,
        query: String
    ): Response<NewsResponse> {
        return service.searchNewsHeadLines(country,query)
    }
}
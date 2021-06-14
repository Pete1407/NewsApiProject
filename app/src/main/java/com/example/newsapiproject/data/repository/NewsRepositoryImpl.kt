package com.example.newsapiproject.data.repository

import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.model.NewsResponse
import com.example.newsapiproject.data.repository.data_source_lmpl.RemoteDataSourceImpl
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val remoteDataSourceImpl : RemoteDataSourceImpl
) : NewsRepository{

    override suspend fun getNewsHeadLines(): Resource<NewsResponse> {
        return responseToResource(remoteDataSourceImpl.getNewsHeadLines())
    }

    private fun responseToResource(outPut : Response<NewsResponse>):Resource<NewsResponse>{
        val success = outPut.isSuccessful
        if(success){
            outPut.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(outPut.message(),null)

    }

    override suspend fun getSearchedNews(searchQuery: String): Resource<NewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNew(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNew(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}
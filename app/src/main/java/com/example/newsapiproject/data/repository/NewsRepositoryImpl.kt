package com.example.newsapiproject.data.repository

import android.util.Log
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.model.NewsResponse
import com.example.newsapiproject.data.repository.data_source.RemoteDataSource
import com.example.newsapiproject.data.repository.data_source_lmpl.RemoteDataSourceImpl
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val remoteDataSource : RemoteDataSource
) : NewsRepository{

    override suspend fun getNewsHeadLines(country : String,page : Int): Resource<NewsResponse> {
        return responseToResource(remoteDataSource.getNewsHeadLines(country,page))
    }

    private fun responseToResource(outPut : Response<NewsResponse>):Resource<NewsResponse>{
        val success = outPut.isSuccessful
        if(success){
            outPut.body()?.let { result ->
                return Resource.Success(result)
            }
        }else{
            Log.i("result","UnSuccess: $success")
        }

        return Resource.Error(outPut.message(),null)

    }

    override suspend fun getSearchedNews(country: String,searchQuery: String): Resource<NewsResponse> {
        return responseToResource(remoteDataSource.searchNewsHeadLines(country,searchQuery))
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
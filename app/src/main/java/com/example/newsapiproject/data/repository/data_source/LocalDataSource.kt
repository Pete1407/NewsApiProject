package com.example.newsapiproject.data.repository.data_source

import com.example.newsapiproject.data.model.Article
import kotlinx.coroutines.flow.Flow

interface LocalDataSource  {

    suspend fun saveArticle(article : Article)

    suspend fun getSavedArticle(): Flow<List<Article>>

    suspend fun deleteArticle(id : Int)
}
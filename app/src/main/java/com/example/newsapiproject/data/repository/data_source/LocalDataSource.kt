package com.example.newsapiproject.data.repository.data_source

import com.example.newsapiproject.data.model.Article

interface LocalDataSource  {

    suspend fun saveArticle(article : Article)
}
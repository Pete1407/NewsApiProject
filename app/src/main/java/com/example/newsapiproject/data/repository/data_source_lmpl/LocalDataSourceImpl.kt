package com.example.newsapiproject.data.repository.data_source_lmpl

import com.example.newsapiproject.data.database.NewsDao
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.repository.data_source.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val newsDao: NewsDao) : LocalDataSource {

    override suspend fun saveArticle(article: Article) {
        newsDao.saveArticle(article)
    }

    override suspend fun getSavedArticle(): Flow<List<Article>> {
        return newsDao.getSavedArticles()
    }
}
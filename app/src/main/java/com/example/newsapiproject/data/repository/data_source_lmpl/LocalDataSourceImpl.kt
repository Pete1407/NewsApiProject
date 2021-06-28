package com.example.newsapiproject.data.repository.data_source_lmpl

import com.example.newsapiproject.data.database.NewsDao
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.repository.data_source.LocalDataSource

class LocalDataSourceImpl(val newsDao: NewsDao) : LocalDataSource {

    override suspend fun saveArticle(article: Article) {
        return newsDao.saveArticle(article)
    }
}
package com.example.newsapiproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapiproject.data.model.Article

@Dao
interface NewsDao {

    @Insert(entity = Article::class,onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article : Article)

    @Query("SELECT * FROM news_db")
    suspend fun getSavedArticles():List<Article>

}
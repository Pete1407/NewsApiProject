package com.example.newsapiproject.data.database

import androidx.room.*
import com.example.newsapiproject.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article : Article)

    @Query("SELECT * FROM article_table")
    fun getSavedArticles(): Flow<List<Article>>

    @Query("DELETE FROM article_table WHERE id = :id")
    fun deleteArticle(id : Int)

}
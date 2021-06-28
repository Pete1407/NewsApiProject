package com.example.newsapiproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.newsapiproject.data.database.converter.SourceConverter
import java.io.Serializable
@Entity(
    tableName = "article_table"
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "content")
    val content: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String?,

    @TypeConverters(SourceConverter::class)
    val source: Source?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?
):Serializable
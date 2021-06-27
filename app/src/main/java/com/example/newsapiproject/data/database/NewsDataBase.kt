package com.example.newsapiproject.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapiproject.data.database.converter.SourceConverter
import com.example.newsapiproject.data.model.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(SourceConverter::class)
abstract class NewsDataBase : RoomDatabase() {

    abstract fun getDao():NewsDao
}
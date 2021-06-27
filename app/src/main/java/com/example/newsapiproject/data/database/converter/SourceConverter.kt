package com.example.newsapiproject.data.database.converter

import androidx.room.TypeConverter
import com.example.newsapiproject.data.model.Source

class SourceConverter {

    @TypeConverter
    fun getNameFromSource(source : Source):String{
        return source.name
    }

    @TypeConverter
    fun getSource(name : String):Source{
        return Source(name,name)
    }
}
package com.example.newsapiproject.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsapiproject.data.model.Source

@ProvidedTypeConverter
class SourceConverter {

    @TypeConverter
    fun getNameFromSource(source : Source):String{
        return source.name
    }

    @TypeConverter
    fun getSourceToName(name : String):Source{
        return Source(name,name)
    }
}
package com.example.newsapiproject.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsapiproject.data.model.Source

class SourceConverter {

    @TypeConverter
    fun getNameFromSource(source : Source?):String{
        source?.let {
            return it.name
        }?:kotlin.run{
            return ""
        }
    }

    @TypeConverter
    fun getSourceFromName(name : String):Source{
        return Source(name,name)
    }
}
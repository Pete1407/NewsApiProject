package com.example.newsapiproject.data.util

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object SimpleDate {
    const val dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    fun setDateFormat(date: String): String {
        val input = SimpleDateFormat(dateFormat)
        val output = SimpleDateFormat("dd/MM/yyyy")
        var d = input.parse(date)
        var formatted = output.format(d)
        return formatted

    }
}
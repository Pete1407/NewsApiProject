package com.example.newsapiproject.data.model

import java.io.Serializable

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
):Serializable
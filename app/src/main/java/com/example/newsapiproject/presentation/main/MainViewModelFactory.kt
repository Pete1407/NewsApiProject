package com.example.newsapiproject.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapiproject.domain.usecase.GetNewsUsecase
import com.example.newsapiproject.domain.usecase.GetSearchNewsUsecase
import com.example.newsapiproject.domain.usecase.SaveNewsUsecase
import java.lang.IllegalArgumentException

class MainViewModelFactory(
    val getNewsUsecase: GetNewsUsecase,
    val searchNewsUsecase: GetSearchNewsUsecase,
    val saveNewsUsecase: SaveNewsUsecase
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(getNewsUsecase,searchNewsUsecase,saveNewsUsecase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
package com.example.newsapiproject.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapiproject.domain.usecase.*
import java.lang.IllegalArgumentException

class MainViewModelFactory(
    val getNewsUsecase: GetNewsUsecase,
    val searchNewsUsecase: GetSearchNewsUsecase,
    val saveNewsUsecase: SaveNewsUsecase,
    val getSaveNewsUsecase: GetSaveNewsUsecase,
    val deleteNewsUsecase: DeleteNewsUsecase
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(
                getNewsUsecase,
                searchNewsUsecase,
                saveNewsUsecase,
                getSaveNewsUsecase,
                deleteNewsUsecase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
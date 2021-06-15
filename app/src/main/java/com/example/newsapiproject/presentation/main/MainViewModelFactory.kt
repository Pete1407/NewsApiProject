package com.example.newsapiproject.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapiproject.domain.usecase.GetNewsUsecase
import java.lang.IllegalArgumentException

class MainViewModelFactory(
    val getNewsUsecase: GetNewsUsecase
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(getNewsUsecase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
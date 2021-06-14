package com.example.newsapiproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapiproject.domain.usecase.GetNewsUsecase
import com.example.newsapiproject.presentation.main.MainViewModel
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
package com.example.newsapiproject.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapiproject.data.model.NewsResponse
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.usecase.GetNewsUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val getNewsUsecase: GetNewsUsecase
) : ViewModel() {
    // initial mutable liveData type Resource<NewsResponse>
    var newsHeadLine : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    fun getNews(country : String,page : Int){
        newsHeadLine.postValue(Resource.Loading())
        // use coroutine by scope of viewmodel
        viewModelScope.launch(Dispatchers.IO) {
            val result = getNewsUsecase.execute(country, page).data
            result?.let {
                newsHeadLine.postValue(Resource.Success(it))
            }
        }
    }

    // check internet that is conntected if not show toast
    





}
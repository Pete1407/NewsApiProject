package com.example.newsapiproject.presentation.main

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapiproject.data.model.Article
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
    var list = ArrayList<Article>()

    fun getNews(context : Context,country : String,page : Int){
      try {
          newsHeadLine.postValue(Resource.Loading())
          if(checkNetworkConnected(context)){
              viewModelScope.launch(Dispatchers.IO) {
                  val result = getNewsUsecase.execute(country, page).data
                  result?.let {
                      newsHeadLine.postValue(Resource.Success(it))
                  }
              }
          }else{
              newsHeadLine.postValue(Resource.Error("Internet is not available"))
          }
      }catch(ex : Exception){
          newsHeadLine.postValue(Resource.Error(ex.message.toString()))
      }


    }

    // check internet that is conntected if not show toast
    private fun checkNetworkConnected(context : Context?) : Boolean{
        context?.let {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager?.let {
                val networkActivty = it.activeNetworkInfo
                if(networkActivty!=null){
                    if(networkActivty.type == ConnectivityManager.TYPE_WIFI){
                        return true
                    }else{
                        return networkActivty.type == ConnectivityManager.TYPE_MOBILE
                    }
                }
            }
        }
        return false

    }

    fun listIsEmpty():Boolean{
        return (list.size > 0)
    }




}
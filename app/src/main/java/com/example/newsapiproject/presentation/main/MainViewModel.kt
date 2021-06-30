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
import com.example.newsapiproject.domain.usecase.GetSaveNewsUsecase
import com.example.newsapiproject.domain.usecase.GetSearchNewsUsecase
import com.example.newsapiproject.domain.usecase.SaveNewsUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class MainViewModel(
    private val getNewsUsecase: GetNewsUsecase,
    private val searchNewsUsecase: GetSearchNewsUsecase,
    private val saveNewsUsecase: SaveNewsUsecase,
    private val getSaveNewsUsecase: GetSaveNewsUsecase
) : ViewModel() {
    // initial mutable liveData type Resource<NewsResponse>
    var newsHeadLine : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var list = ArrayList<Article>()
    var savedList = MutableLiveData<ArrayList<Article>>()
    fun getNews(context : Context,country : String,page : Int){
      try {
          newsHeadLine.postValue(Resource.Loading())
          if(checkNetworkConnected(context)){
              viewModelScope.launch(Dispatchers.IO) {
                  val result = getNewsUsecase.execute(country, page).data
                  Log.i("result","result --> $result")
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

    fun getSearchNewsHeadLines(query : String){
        newsHeadLine.postValue(Resource.Loading())
     try {
         viewModelScope.launch(Dispatchers.IO){
             val request = searchNewsUsecase.execute("us",query)
             request.data?.let {
                 newsHeadLine.postValue(Resource.Success(request.data))
             }
         }
     }catch (ex : Exception){
         newsHeadLine.postValue(Resource.Error(ex.message.toString()))
     }
    }

    fun saveFavoriteArticle(article : Article){
        viewModelScope.launch(Dispatchers.IO){
            saveNewsUsecase.execute(article)
        }
    }

    fun getSavedArticleList(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = getSaveNewsUsecase.execute()
            result.collect {
                savedList.postValue(ArrayList(it))
            }

        }
    }

}
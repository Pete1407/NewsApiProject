package com.example.newsapiproject.data.util

interface BaseState {
    fun initViewModel()
    fun showLoading()
    fun hideLoading()
}
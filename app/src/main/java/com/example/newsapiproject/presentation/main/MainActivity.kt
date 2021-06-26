package com.example.newsapiproject.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapiproject.R
import com.example.newsapiproject.data.util.BaseState
import com.example.newsapiproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),BaseState {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController : NavController
    @Inject
    lateinit var factory : MainViewModelFactory
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        navController = navHost.navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController,AppBarConfiguration(navController.graph))
        initViewModel()

    }

    private fun setViewModel(){
        viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)
    }

    override fun initViewModel() {
        setViewModel()
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
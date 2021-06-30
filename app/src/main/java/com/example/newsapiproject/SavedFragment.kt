package com.example.newsapiproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.newsapiproject.data.util.BaseStateFragment
import com.example.newsapiproject.databinding.FragmentSavedBinding
import com.example.newsapiproject.presentation.main.MainActivity
import com.example.newsapiproject.presentation.main.MainViewModel


class SavedFragment : Fragment(),BaseStateFragment {
    private lateinit var binding : FragmentSavedBinding
    private lateinit var vm : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        getNewsList()
    }

    private fun initViewModel(){
        vm = (requireActivity() as MainActivity).viewModel
    }

    private fun getNewsList(){
        vm.getSavedArticleList()
        vm.savedList.observe(viewLifecycleOwner, Observer {
            Log.d("debug","data --> ${it.size}")
            it.forEach { item ->
                Log.d("debug","${item.title}")
            }

        })
    }

    override fun initListener() {

    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }


}
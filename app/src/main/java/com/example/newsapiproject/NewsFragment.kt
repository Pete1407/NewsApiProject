package com.example.newsapiproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapiproject.data.model.NewsResponse
import com.example.newsapiproject.data.util.BaseStateFragment
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.databinding.FragmentNewsBinding
import com.example.newsapiproject.presentation.main.MainActivity
import com.example.newsapiproject.presentation.main.MainViewModel
import com.example.newsapiproject.presentation.main.adapter.NewsAdapter


class NewsFragment : Fragment(),BaseStateFragment {

    private lateinit var binding : FragmentNewsBinding
    lateinit var vm : MainViewModel
    private lateinit var adapter : NewsAdapter
    private var page : Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initUI()
        vm.getNews(requireContext(), COUNTRY,page)
        vm.newsHeadLine.observe(viewLifecycleOwner,{ resource ->
            when(resource){
                is Resource.Success -> {
                    hideLoading()
                    resource.data?.let {
                        var newData = ArrayList(it.articles)
                        adapter.updateData(newData)
                    }

                }

                is Resource.Loading -> {
                    showLoading()
                    Log.d("debug","loading")
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(),"${resource.message}",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initViewModel(){
        vm = (requireActivity() as MainActivity).viewModel
    }

    private fun initUI(){
        adapter = NewsAdapter(vm.list)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    companion object{
        const val COUNTRY = "us"
    }
}
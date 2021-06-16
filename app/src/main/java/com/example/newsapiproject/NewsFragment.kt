package com.example.newsapiproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapiproject.databinding.FragmentNewsBinding
import com.example.newsapiproject.presentation.main.MainActivity
import com.example.newsapiproject.presentation.main.MainViewModel


class NewsFragment : Fragment() {

    private lateinit var binding : FragmentNewsBinding
    lateinit var viewModel : MainViewModel

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
    }

    private fun initViewModel(){
        viewModel = (requireActivity() as MainActivity).vm
    }
}
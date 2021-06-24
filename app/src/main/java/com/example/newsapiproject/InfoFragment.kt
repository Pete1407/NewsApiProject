package com.example.newsapiproject

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.util.BaseStateFragment
import com.example.newsapiproject.databinding.FragmentInfoBinding

class InfoFragment : Fragment(),BaseStateFragment {
    private lateinit var binding : FragmentInfoBinding
    private var article : Article? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args : InfoFragmentArgs by navArgs()
        article = args.keySelect
        Log.d("debug","$article")
        if(article!!.url!=" "){
            binding.webView.loadUrl(article!!.url)
        }

    }

    override fun initListener() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
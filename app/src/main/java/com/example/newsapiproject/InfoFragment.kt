package com.example.newsapiproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.navArgs
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.util.BaseStateFragment
import com.example.newsapiproject.databinding.FragmentInfoBinding

class InfoFragment : Fragment(),BaseStateFragment {
    private lateinit var binding : FragmentInfoBinding
    private var article : Article? = null

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
        article!!.url?.let {
            if(it.isNullOrBlank()){
                binding.webView.loadUrl(it)
            }
        }

    }

    override fun initListener() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
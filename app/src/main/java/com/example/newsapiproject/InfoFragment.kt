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
import com.example.newsapiproject.presentation.main.MainActivity
import com.example.newsapiproject.presentation.main.MainViewModel
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment(),BaseStateFragment {
    private lateinit var binding : FragmentInfoBinding
    private var article : Article? = null

    private lateinit var vm : MainViewModel
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
        initViewModel()
        initListener()
        val args : InfoFragmentArgs by navArgs()
        getValueFromNews(args)
    }

    private fun getValueFromNews(args : InfoFragmentArgs){
        article = args.keySelect
        Log.d("debug","url of web --> ${article!!.url}")
        article!!.url?.let { binding.webView.loadUrl(it) }
    }

    private fun initViewModel(){
        vm = (requireActivity() as MainActivity).viewModel
    }

    override fun initListener() {
        binding.likeButton.setOnClickListener {
            article?.let { item ->
                saveArticle(item)
            }
            val snackBar = Snackbar.make(it,resources.getString(R.string.save_article),Snackbar.LENGTH_SHORT)
            snackBar.show()
        }
    }

    private fun saveArticle(article : Article){
        vm.saveFavoriteArticle(article)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
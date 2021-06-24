package com.example.newsapiproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiproject.data.util.BaseStateFragment
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.databinding.FragmentNewsBinding
import com.example.newsapiproject.presentation.main.MainActivity
import com.example.newsapiproject.presentation.main.MainViewModel
import com.example.newsapiproject.presentation.main.MarginItemDecoration
import com.example.newsapiproject.presentation.main.adapter.NewsAdapter


class NewsFragment : Fragment(),BaseStateFragment {

    private lateinit var binding : FragmentNewsBinding
    lateinit var vm : MainViewModel
    private lateinit var adapter : NewsAdapter
    private var page : Int = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    var pages = 0
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
                        Log.i("result","totalResult --> ${it.totalResults}")
                        adapter.updateData(newData)
                        if(it.totalResults %20 == 0){
                            pages = it.totalResults / 20
                        }else{
                            pages = it.totalResults / 20 + 1
                        }
                        isLastPage = page == pages
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
        initListener()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_small)))
        binding.recyclerView.addOnScrollListener(scrollListener)
        binding.recyclerView.adapter = adapter

    }

    override fun initListener() {
        Log.i("debug","click choose item ...")
        adapter.setOnClickListenr { data ->
            val bundle = Bundle()
            bundle.apply {
                this.putSerializable(PARAM_SELECTED,data)
            }
            findNavController().navigate(R.id.action_newsFragment_to_infoFragment,bundle)
        }
    }

    override fun showLoading() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }

    val scrollListener = object : RecyclerView.OnScrollListener(){

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
            // จำนวนของ item ทั้งหมดตต่อ 1 หน้า (ในที่นี้คือ 1 page = 20 item)
            val currentList = layoutManager.itemCount
            // จำนวนของ item ที่มองเห็นในหน้าจอ ณ ขณะนั้น
            val totalVisibleItem = layoutManager.childCount
            val topItemPosition = layoutManager.findFirstVisibleItemPosition()
            val checkLastItem = topItemPosition + totalVisibleItem >= currentList
            val shouldPaginate = !isLoading && !isLastPage && checkLastItem && isScrolling
            if(shouldPaginate){
                page++
                vm.getNews(requireContext(),"us",page)
                isScrolling = false
            }
        }
    }

    companion object{
        const val COUNTRY = "us"
        const val PARAM_SELECTED = "key_select"
    }
}
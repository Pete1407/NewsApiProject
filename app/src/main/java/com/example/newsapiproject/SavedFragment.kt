package com.example.newsapiproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapiproject.data.util.BaseStateFragment
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.data.util.SwipeToDeleteCallBack
import com.example.newsapiproject.databinding.FragmentSavedBinding
import com.example.newsapiproject.presentation.main.MainActivity
import com.example.newsapiproject.presentation.main.MainViewModel
import com.example.newsapiproject.presentation.main.MarginItemDecoration
import com.example.newsapiproject.presentation.main.adapter.ItemAdapter


class SavedFragment : Fragment(), BaseStateFragment {
    private lateinit var binding: FragmentSavedBinding
    private lateinit var vm: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        getNewsList()
    }

    private fun initViewModel() {
        vm = (requireActivity() as MainActivity).viewModel
    }

    private fun getNewsList() {
        vm.getSavedArticleList()
        vm.savedList.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    resource.data?.let {
                        val itemAdapter = ItemAdapter(it)
                        val layoutManager = LinearLayoutManager(requireContext())
                        binding.recyclerView.apply {
                            this.addItemDecoration(
                                MarginItemDecoration(
                                    requireActivity().resources.getDimensionPixelSize(
                                        R.dimen.spacing_small
                                    )
                                )
                            )
                            val callback = object : SwipeToDeleteCallBack() {
                                override fun onSwiped(
                                    viewHolder: RecyclerView.ViewHolder,
                                    direction: Int
                                ) {
                                    val positionFordb = itemAdapter.list[viewHolder.adapterPosition]
                                    // delete
                                    vm.deleteArticleById(positionFordb)
                                    Log.d(
                                        "debug",
                                        "to delete -->id: ${positionFordb.id} title:  ${positionFordb.title}"
                                    )
                                    val positionForList = viewHolder.adapterPosition
                                    itemAdapter.deleteItem(positionForList)
                                }
                            }
                            this.layoutManager = layoutManager
                            val itemTouchHelper = ItemTouchHelper(callback)
                            itemTouchHelper.attachToRecyclerView(this)
                            this.adapter = itemAdapter
                        }
                    }
                }
                else -> {
                }
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
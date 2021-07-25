package com.example.newsapp.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.base.EmptyViewModel
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.databinding.FragmentSplashScreenBinding
import com.example.newsapp.feature.main.MainActivity
import com.example.newsapp.model.schema.News
import java.util.*
import kotlin.concurrent.schedule

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(), NewsAdapter.HomeListener {

    private val newsAdapter = NewsAdapter()

    override fun setViewModel() {
        mViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun getMainContainer(): View? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).mViewDataBinding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        initAdapter()
        mViewModel.getNews(requireContext(), ::onSuccess, ::onFailed)
    }

    override fun onNewsClickListener(data: News) {
        Log.d("Gson hasil", "KLIK NIH")
    }

    fun initAdapter(){
        newsAdapter.setOnClickListener(this)
        mViewDataBinding.rvNews.adapter = newsAdapter
        mViewDataBinding.rvNews.layoutManager = LinearLayoutManager(context)
        mViewDataBinding.rvNews.addItemDecoration(
                DividerItemDecoration(
                        context,
                DividerItemDecoration.VERTICAL)
        )
    }

    fun onSuccess(){
        newsAdapter.updateData(mViewModel.news)
    }

    fun onFailed(){
        Log.d("GAGAL", "back to fragment")
    }
}
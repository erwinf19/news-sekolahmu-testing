package com.example.newsapp.feature.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.feature.main.MainActivity
import com.example.newsapp.model.schema.News
import com.example.newsapp.utils.GlobalConstant
import com.example.newsapp.utils.PreferenceHelper

class NewsFragment : BaseFragment<NewsViewModel, FragmentNewsBinding>(), NewsAdapter.HomeListener {

    private val newsAdapter = NewsAdapter()

    override fun setViewModel() {
        mViewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsBinding =
        FragmentNewsBinding.inflate(layoutInflater, container, false)

    override fun getMainContainer(): View? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).mViewDataBinding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        (activity as MainActivity).loadingDialog.init()
        pref = PreferenceHelper(requireContext())
        initAdapter()
        if(!pref.getSync()){
            (activity as MainActivity).loadingDialog.startLoading()
        }
        mViewModel.getNews(requireContext(), ::onSuccess, ::onFailed)

        mViewDataBinding.swipeRefreshLayout.setOnRefreshListener{
            mViewDataBinding.swipeRefreshLayout.isRefreshing = false
            pref.setSync(false)
            mViewModel.getNews(requireContext(), ::onSuccess, ::onFailed)
        }
    }

    override fun onNewsClickListener(data: News) {
        var bundle : Bundle = Bundle()
        if(data!=null){
            if(data.localid!=null){
                bundle.putString(GlobalConstant.Bundle.NEWS_ID, data.localid)
                goToFragment(requireActivity().supportFragmentManager, NewsDetailFragment(), bundle, null)
            }
        }
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
        (activity as MainActivity).loadingDialog.stopLoading()
        mViewDataBinding.rvNews.visibility = View.VISIBLE
        mViewDataBinding.tvNetworkError.visibility = View.GONE
        newsAdapter.updateData(mViewModel.news)
    }

    fun onFailed(){
        (activity as MainActivity).loadingDialog.stopLoading()
        mViewDataBinding.rvNews.visibility = View.GONE
        mViewDataBinding.tvNetworkError.visibility = View.VISIBLE
    }
}
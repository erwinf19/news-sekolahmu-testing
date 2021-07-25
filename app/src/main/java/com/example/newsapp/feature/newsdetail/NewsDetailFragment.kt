package com.example.newsapp.feature.news

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.databinding.FragmentNewsDetailBinding
import com.example.newsapp.feature.main.MainActivity
import com.example.newsapp.model.schema.Multimedia
import com.example.newsapp.model.schema.News
import com.example.newsapp.utils.GlobalConstant

class NewsDetailFragment : BaseFragment<NewsDetailViewModel, FragmentNewsDetailBinding>(){

    private var newsId : String? = null

    override fun setViewModel() {
        mViewModel = ViewModelProvider(requireActivity()).get(NewsDetailViewModel::class.java)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsDetailBinding =
        FragmentNewsDetailBinding.inflate(layoutInflater, container, false)

    override fun getMainContainer(): View? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as MainActivity).mViewDataBinding.toolbar.setNavigationOnClickListener {
            (activity as MainActivity).onBackPressed()
        }

        var bundle = this.arguments
        if (bundle!=null) {
            newsId = bundle.getString(GlobalConstant.Bundle.NEWS_ID)
            mViewModel.getNewsDetail(newsId!!)
            mViewModel.getMultimedia(newsId!!)
        }
        initView(mViewModel.news!!, mViewModel.multimedia)
    }

    fun initView(news: News, multimedia: Multimedia){
        mViewDataBinding.tvTitle.text = news.title
        mViewDataBinding.tvBreadcrumb.text = news.section
        mViewDataBinding.tvReporter.text = getString(
            R.string.text_reporter,
            news.byline,
            news.updated_date
        )

        if(multimedia.url!=null){
            Glide.with(requireContext())
                .load(multimedia.url)
                .centerCrop()
                .placeholder(R.drawable.img_notfound)
                .into(mViewDataBinding.ivNews)
        }
        mViewDataBinding.tvCaption.text = multimedia.caption
        mViewDataBinding.tvDesc.text = news.desc

        mViewDataBinding.ivShare.setOnClickListener {
            Toast.makeText(requireContext(), "Not Implemented Yet", Toast.LENGTH_SHORT).show()
        }
    }



}
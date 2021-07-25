package com.example.newsapp.feature.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ListNewsBinding
import com.example.newsapp.model.schema.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.HomeAdapterHolder>() {

    interface HomeListener {
        fun onNewsClickListener(news: News)
    }

    lateinit var items: ArrayList<News>
    lateinit var listener: HomeListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterHolder {
        val defaultBinding: ListNewsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_news,
                parent,
                false
        )
        return HomeAdapterHolder(defaultBinding)
    }

    override fun onBindViewHolder(holder: HomeAdapterHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            listener.onNewsClickListener(
                    items[position]
            )
        }
    }

    override fun getItemCount(): Int = if (::items.isInitialized) items.size else 0

    fun updateData(data: ArrayList<News>?) {
        if (!data.isNullOrEmpty()) {
            items = data
            notifyDataSetChanged()
        }
    }

    fun setOnClickListener(listener: HomeListener) {
        this.listener = listener
    }

    class HomeAdapterHolder constructor(private val viewBinding: ListNewsBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(news: News) {
            val context = viewBinding.root.context
            viewBinding.tvTitleNews.text = news.title
            viewBinding.tvAbstract.text = news.abstract
            if(news.thumbnail_standard!=null){
                Glide.with(context)
                        .load(news.thumbnail_standard)
                        .centerCrop()
                        .into(viewBinding.ivTitleImage)
            }
        }
    }
}
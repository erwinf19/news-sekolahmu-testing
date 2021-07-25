package com.example.newsapp.feature.news

import android.util.Log
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.model.repo.MultimediaRepo
import com.example.newsapp.model.repo.NewsRepo
import com.example.newsapp.model.schema.Multimedia
import com.example.newsapp.model.schema.News

class NewsDetailViewModel : BaseViewModel() {

    var news: News = News()
    var multimedia: Multimedia = Multimedia()

    fun getNewsDetail(newsId : String){
        var oneNews = NewsRepo(realm).getNewsFromID(newsId)!!
        if(oneNews != null){
            news = oneNews
        }
    }

    fun getMultimedia(newsId : String){
        Log.d("NID", "NID " +newsId)
        if(newsId!=null){
            var mm = MultimediaRepo(realm).getOneMultimediaFromNews(newsId)
            if(mm != null){
                multimedia = mm
            }
        }
    }
}
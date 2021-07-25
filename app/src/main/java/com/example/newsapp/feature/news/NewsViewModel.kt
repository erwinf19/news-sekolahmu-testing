package com.example.newsapp.feature.news

import android.content.Context
import android.util.Log
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.model.repo.NewsRepo
import com.example.newsapp.model.schema.News
import com.example.newsapp.rest.param.result.NewsResponse
import com.example.newsapp.utils.PreferenceHelper
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : BaseViewModel() {

    var news: ArrayList<News>? = null

    fun getNews(context : Context, onSuccess : () -> Unit, onFailed : () -> Unit) {
        pref = PreferenceHelper(context)
        if (!pref.getSync()) {
            var call = apiService.getTopNews()
            call.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {

                    if (response != null) {
                        var body = response.body()
                        if(body!=null){
                            if (body.status.equals("OK")) {
                                var newData = body.results
                                NewsRepo(realm).insertUpdateNews(newData!!)
                                news = NewsRepo(realm).getAllNews()
                                pref.setSync(true)
                            }
                        }
                    }
                    onSuccess()
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    onFailed()
                }
            })
        } else {
            news = NewsRepo(realm).getAllNews()
            onSuccess()
        }
    }
}
package com.example.newsapp.feature.home

import android.content.Context
import android.util.Log
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.model.action.NewsAction
import com.example.newsapp.model.schema.News
import com.example.newsapp.rest.param.result.NewsResponse
import com.example.newsapp.utils.PreferenceHelper
import com.google.gson.Gson
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : BaseViewModel() {

    var news: ArrayList<News>? = null

    fun getNews(context : Context, onSuccess : () -> Unit, onFailed : () -> Unit) {
        pref = PreferenceHelper(context)
        if (!pref.getSync()) {
            var call = apiService.getTopNews()
            call.enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {

                    if (response != null) {
                        var body = response.body()
                        if (body!!.status.equals("OK")) {
                            news = body!!.results
                            NewsAction(realm).insertUpdateNews(news!!)
                        }
                    }
                    onSuccess()
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    onFailed()
                }
            })
        } else {
            news = NewsAction(realm).getAllNews()
            onSuccess()
        }
    }
}
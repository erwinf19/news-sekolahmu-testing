package com.example.newsapp.rest.api

import com.example.newsapp.rest.param.result.NewsResponse
import com.example.newsapp.utils.GlobalConstant
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {

    @GET("news/v3/content/all/all.json?api-key=" + GlobalConstant.API_KEY)
    fun getTopNews() : Call<NewsResponse>

}
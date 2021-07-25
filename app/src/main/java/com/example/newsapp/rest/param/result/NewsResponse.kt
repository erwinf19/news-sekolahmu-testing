package com.example.newsapp.rest.param.result

import com.example.newsapp.model.schema.News

class NewsResponse : DefaultResponse() {

    var results : ArrayList<News>? = null

}
package com.example.newsapp.rest.param.result

import com.example.newsapp.model.schema.News

class NewsResponse : DefaultResponse() {

    var result : List<News>? = null

}
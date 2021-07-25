package com.example.newsapp.model.action

import com.example.newsapp.NewsApp
import com.example.newsapp.model.schema.Multimedia
import com.example.newsapp.model.schema.News
import io.realm.Realm
import java.util.*
import kotlin.collections.ArrayList

class MultimediaAction(realm : Realm) {

    var realm = realm

    fun getAllMultimedia() : ArrayList<Multimedia>{
        return ArrayList(realm.where(Multimedia::class.java).findAll())
    }

    fun getMultimediaFromNews(newsId : String) : ArrayList<Multimedia>?{
        var news : News? = realm.where(News::class.java).equalTo("id", newsId).findFirst()
        if(news!=null){
            var multimedia : ArrayList<Multimedia> = ArrayList(news.multimedia)
            return multimedia
        }
        return null
    }

    fun insertUpdateMultimedia(newsId : String, multimedia : ArrayList<Multimedia>){
        if(multimedia != null){
            for(i in multimedia!!){
                //openAPI ga ada ID nya :( jadi di query pake url aja deh
                val data = realm.where(Multimedia::class.java).equalTo("url", i.url).findFirst()
                if (data != null) {
                    data.newsid = newsId
                    data.format = i.format
                    data.height = i.height
                    data.width = i.width
                    data.type = i.type
                    data.subtype = i.subtype
                    data.caption = i.caption
                    data.copyright = i.copyright
                } else {
                    val newData = realm.createObject(Multimedia::class.java, UUID.randomUUID().toString())
                    newData.url = i.url
                    newData.newsid = newsId
                    newData.format = i.format
                    newData.height = i.height
                    newData.width = i.width
                    newData.type = i.type
                    newData.subtype = i.subtype
                    newData.caption = i.caption
                    newData.copyright = i.copyright
                }
            }
        }
    }

}
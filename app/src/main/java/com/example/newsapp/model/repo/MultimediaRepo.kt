package com.example.newsapp.model.repo

import android.util.Log
import com.example.newsapp.model.schema.Multimedia
import com.example.newsapp.model.schema.News
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmResults
import io.realm.Sort
import java.util.*
import kotlin.collections.ArrayList

class MultimediaRepo(realm : Realm) {

    var realm = realm

    fun getAllMultimedia() : ArrayList<Multimedia>{
        return ArrayList(realm.where(Multimedia::class.java).findAll())
    }

    fun getMultimediaFromNews(newsId : String) : ArrayList<Multimedia>?{
        var multimedia : ArrayList<Multimedia> = ArrayList(realm.where(Multimedia::class.java).equalTo("newsid", newsId).findAll())
        return multimedia
    }

    fun getOneMultimediaFromNews(newsId : String) : Multimedia?{
        Log.d("SAMPE", "CHECK : MASUK NIH")
        var multimedia : RealmResults<Multimedia> = realm.where(Multimedia::class.java).equalTo("newsid", newsId).sort("width", Sort.DESCENDING).findAll()
        Log.d("SAMPE", "QUERY")
        if(multimedia.size>0){
            return multimedia.get(0)
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

    fun deleteAllMultimedia(){
        realm.executeTransaction {
            var data = it.where(Multimedia::class.java).findAll()
            data.deleteAllFromRealm()
        }
    }

}
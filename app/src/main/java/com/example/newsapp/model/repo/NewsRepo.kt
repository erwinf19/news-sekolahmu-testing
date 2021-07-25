package com.example.newsapp.model.repo

import android.util.Log
import com.example.newsapp.model.schema.News
import io.realm.Realm
import java.util.*
import kotlin.collections.ArrayList

class NewsRepo(realm : Realm) {

    var realm = realm

    fun getAllNews() : ArrayList<News>{
        return ArrayList(realm.where(News::class.java).findAll())
    }

    fun getNewsFromID(id : String) : News?{
        return realm.where(News::class.java).equalTo("localid", id).findFirst()
    }

    fun insertUpdateNews(news : ArrayList<News>){
        realm.executeTransaction {
            for (i in news) {
                //openAPI ga ada ID nya :( jadi di query pake title aja deh
                val data = it.where(News::class.java).equalTo("title", i.title).findFirst()
                if (data != null) {
                    data.desc = i.desc
                    data.section = i.section
                    data.subsection = i.subsection
                    data.byline = i.byline
                    data.url = i.url
                    data.thumbnail_standard = i.thumbnail_standard
                    data.updated_date = i.updated_date
                    data.item_type = i.item_type
                    data.material_type_facet = i.material_type_facet

                    if(i.multimedia!=null){
                        MultimediaRepo(it).insertUpdateMultimedia(data.localid!!, ArrayList(i.multimedia))
                    }
                } else {
                    var new_id : String = UUID.randomUUID().toString()
                    val newData = it.createObject(News::class.java, new_id)
                    newData.title = i.title
                    newData.section = i.section
                    newData.desc = i.desc
                    newData.subsection = i.subsection
                    newData.byline = i.byline
                    newData.url = i.url
                    newData.thumbnail_standard = i.thumbnail_standard
                    newData.updated_date = i.updated_date
                    newData.item_type = i.item_type
                    newData.material_type_facet = i.material_type_facet
                    if(i.multimedia!=null){
                        MultimediaRepo(it).insertUpdateMultimedia(new_id, ArrayList(i.multimedia))
                    }
                }
            }
        }
    }

    fun deleteAllNews(){
        realm.executeTransaction {
            var data = it.where(News::class.java).findAll()
            data.deleteAllFromRealm()
        }
    }

}
package com.example.newsapp.model.schema

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Multimedia : RealmObject(){
    @PrimaryKey
    var id : String? = null
    var newsid : String? = null
    @SerializedName("url")
    var url : String? = null
    @SerializedName("format")
    var format : String? = null
    @SerializedName("height")
    var height : String? = null
    @SerializedName("width")
    var width : String? = null
    @SerializedName("type")
    var type : String? = null
    @SerializedName("subtype")
    var subtype : String? = null
    @SerializedName("caption")
    var caption : String? = null
    @SerializedName("copyright")
    var copyright : String? = null

}
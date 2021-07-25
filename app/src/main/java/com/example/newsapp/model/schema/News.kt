package com.example.newsapp.model.schema

import com.google.gson.annotations.SerializedName
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class News : RealmObject(){

    @PrimaryKey
    var id : String? = null
    @SerializedName("title")
    var title : String? = null
    @SerializedName("section")
    var section : String? = null
    @SerializedName("subsection")
    var subsection : String? = null
    @SerializedName("abstract")
    var abstract : String? = null
    @SerializedName("url")
    var url : String? = null
    @SerializedName("byline")
    var byline : String? = null
    @SerializedName("item_type")
    var item_type : String? = null
    @SerializedName("thumbnail_standard")
    var thumbnail_standard : String? = null
    @SerializedName("updated_date")
    var updated_date : String? = null
    @SerializedName("material_type_facet")
    var material_type_facet : String? = null
    @SerializedName("multimedia")
    var multimedia : RealmList<Multimedia>? = RealmList()

}
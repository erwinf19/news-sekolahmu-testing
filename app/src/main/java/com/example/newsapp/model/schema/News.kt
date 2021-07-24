package com.example.newsapp.model.schema

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class News : RealmObject(){

    @PrimaryKey
    var id : String? = null
    var title : String? = null;
    var section : String? = null;
    var subsection : String? = null;
    var abstract : String? = null;
    var url : String? = null;
    var byline : String? = null;
    var item_type : String? = null;
    var thumbnail_standard : String? = null;
    var updated_date : String? = null;
    var material_type_facet : String? = null;
    var multimedia : RealmList<Multimedia>? = RealmList()

}
package com.example.newsapp.model.schema

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Multimedia : RealmObject(){
    @PrimaryKey
    var id : String? = null
    var url : String? = null;
    var format : String? = null;
    var height : String? = null;
    var width : String? = null;
    var type : String? = null;
    var subtype : String? = null;
    var caption : String? = null;
    var copyright : String? = null;

}
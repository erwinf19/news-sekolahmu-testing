package com.example.newsapp.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context : Context) {

    private val pref = "NewsApp"
    private val SYNC_NEWS = "SYNC_NEWS"
    private var sharedPref : SharedPreferences = context.getSharedPreferences(pref, Context.MODE_PRIVATE)
    private var editor : SharedPreferences.Editor = sharedPref.edit()

    init{
        editor.apply()
    }

    fun setSync(status : Boolean){
        editor.putBoolean(SYNC_NEWS, status)
        editor.commit()
    }

    fun getSync(): Boolean{
        return sharedPref.getBoolean(SYNC_NEWS, false)
    }

}
package com.example.newsapp.base

import androidx.lifecycle.ViewModel
import com.example.newsapp.rest.api.ApiService
import com.example.newsapp.rest.module.RetrofitModule
import com.example.newsapp.utils.PreferenceHelper
import io.realm.Realm

abstract class BaseViewModel : ViewModel() {

   var realm : Realm = Realm.getDefaultInstance()
   var apiService : ApiService = RetrofitModule().getDefaultInterface()
   lateinit var pref : PreferenceHelper

}
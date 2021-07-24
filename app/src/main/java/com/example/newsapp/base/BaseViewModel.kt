package com.example.newsapp.base

import androidx.lifecycle.ViewModel
import io.realm.Realm

abstract class BaseViewModel : ViewModel() {

   var realm : Realm = Realm.getDefaultInstance();

}
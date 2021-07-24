package com.example.newsapp

import android.app.Application
import com.example.newsapp.model.Migration
import io.realm.Realm
import io.realm.RealmConfiguration

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        val configuration = RealmConfiguration.Builder().name("news.realm").schemaVersion(1).migration(Migration()).build()
        Realm.setDefaultConfiguration(configuration)
        Realm.getInstance(configuration)
    }

}
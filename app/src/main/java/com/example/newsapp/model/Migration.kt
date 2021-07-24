package com.example.newsapp.model

import io.realm.DynamicRealm
import io.realm.RealmMigration

open class Migration : RealmMigration {

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        val schema = realm?.schema
        if (oldVersion == 1.toLong()) {

        }
    }
}
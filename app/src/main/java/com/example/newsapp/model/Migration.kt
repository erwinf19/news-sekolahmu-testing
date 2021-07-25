package com.example.newsapp.model

import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmList
import io.realm.RealmMigration

open class Migration : RealmMigration {

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        val schema = realm?.schema
        if (oldVersion == 1.toLong()) {
            schema!!.create("Multimedia")
                .addField("id", String::class.java, FieldAttribute.PRIMARY_KEY)
                .addField("newsid", String::class.java)
                .addField("url", String::class.java)
                .addField("format", String::class.java)
                .addField("height", String::class.java)
                .addField("width", String::class.java)
                .addField("type", String::class.java)
                .addField("subtype", String::class.java)
                .addField("caption", String::class.java)
                .addField("copyright", String::class.java)

            schema!!.create("News")
                .addField("localid", String::class.java, FieldAttribute.PRIMARY_KEY)
                .addField("title", String::class.java)
                .addField("desc", String::class.java)
                .addField("section", String::class.java)
                .addField("subsection", String::class.java)
                .addField("url", String::class.java)
                .addField("byline", String::class.java)
                .addField("item_type", String::class.java)
                .addField("thumbnail_standard", String::class.java)
                .addField("updated_date", String::class.java)
                .addField("material_type_facet", String::class.java)
                .addRealmListField("multimedia", schema.get("Multimedia"))
        }
    }
}
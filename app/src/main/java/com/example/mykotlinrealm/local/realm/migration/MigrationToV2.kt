package com.example.mykotlinrealm.local.realm.migration

import com.example.mykotlinrealm.local.entity.BookWrapper
import io.realm.FieldAttribute
import io.realm.RealmSchema

class MigrationToV2 constructor(private val schema: RealmSchema?) {
    fun init() {
        schema?.create(BookWrapper.BOOK_WRAPPER_CLASS)
            ?.addField(BookWrapper.COL_ID, Int::class.java, FieldAttribute.PRIMARY_KEY)
            ?.addField(BookWrapper.COL_TITLE, String::class.java)
            ?.addField(BookWrapper.COL_DESCRIPTION, String::class.java)
    }
}
package com.example.mykotlinrealm.local.realm.migration

import com.example.mykotlinrealm.local.entity.BookWrapper
import io.realm.RealmSchema

class MigrationToV2 constructor(private val schema: RealmSchema?) {
    fun init() {
        schema?.create(BookWrapper.BOOK_WRAPPER_CLASS)
            ?.addField(BookWrapper.BOOK_COL_ID, Int::class.java)
            ?.addField(BookWrapper.BOOK_COL_TITLE, String::class.java)
            ?.addField(BookWrapper.BOOK_COL_DESCRIPTION, String::class.java)
    }
}
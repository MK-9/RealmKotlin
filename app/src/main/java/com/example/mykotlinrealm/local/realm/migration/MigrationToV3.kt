package com.example.mykotlinrealm.local.realm.migration

import com.example.mykotlinrealm.local.entity.BookFile
import io.realm.FieldAttribute
import io.realm.RealmSchema

class MigrationToV3 constructor(private val schema: RealmSchema?) {
    fun init() {
        schema?.create(BookFile.BOOK_FILE_CLASS)
            ?.addField(BookFile.COL_ID, Int::class.java, FieldAttribute.PRIMARY_KEY)
            ?.addField(BookFile.COL_TITLE, String::class.java)
            ?.addField(BookFile.COL_STORAGE_PATH, String::class.java)
    }
}
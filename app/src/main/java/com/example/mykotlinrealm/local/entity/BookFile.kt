package com.example.mykotlinrealm.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class BookFile(
    @PrimaryKey var id: Int = 0,
    var title: String? = null,
    var storagePath: String? = null
) : RealmObject() {

    companion object {
        const val BOOK_FILE_CLASS = "BookFile"

        const val COL_ID = "id"
        const val COL_TITLE = "title"
        const val COL_STORAGE_PATH = "storagePath"
    }
}
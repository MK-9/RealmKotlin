package com.example.mykotlinrealm.local.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookWrapper(
    @PrimaryKey
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null
) : RealmObject() {

    companion object {
        const val BOOK_WRAPPER_CLASS = "BookWrapper"
        const val COL_ID = "id"
        const val COL_TITLE = "title"
        const val COL_DESCRIPTION = "description"
    }
}
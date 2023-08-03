package com.example.mykotlinrealm.local.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class NewBookWrapper : RealmObject {

    @PrimaryKey
    var id: Int = 0

    var title: String? = null

    var description: String? = null

    companion object {
        const val BOOK_WRAPPER_CLASS = "NewBookWrapper"
        const val COL_ID = "id"
        const val COL_TITLE = "title"
        const val COL_DESCRIPTION = "description"
    }
}
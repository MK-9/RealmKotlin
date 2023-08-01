package com.example.mykotlinrealm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Account(
    @PrimaryKey
    var id: Int = 0,
    var userId: Int = 0,
    var title: String? = null
) : RealmObject() {

    companion object {
        const val COL_ID = "id"
        const val COL_USER_ID = "userId"
        const val COL_TITLE = "title"
    }
}
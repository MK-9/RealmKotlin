package com.example.mykotlinrealm.local.realm.migration

import com.example.mykotlinrealm.local.entity.Account
import io.realm.RealmSchema

class MigrationToV1 constructor(private val schema: RealmSchema?) {
    fun init() {
        schema?.get(Account.ACCOUNT_CLASS)
            ?.addField(Account.COL_SUBTITLE, String::class.java)
    }
}
package com.example.mykotlinrealm.local.realm

import android.util.Log
import com.example.mykotlinrealm.local.entity.Account
import io.realm.DynamicRealm
import io.realm.RealmMigration

class DefaultRealmMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        Log.d("aaa", "DefaultRealmDB: oldVersion:$oldVersion, newVersion:$newVersion")
        val schema = realm.schema
        var lastVersion = oldVersion.toInt()
        if (lastVersion == 0) {
            schema.get(Account.ACCOUNT_CLASS)
                ?.addField(Account.COL_SUBTITLE, String::class.java)
        }
    }
}
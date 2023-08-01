package com.example.mykotlinrealm.local.realm

import android.util.Log
import com.example.mykotlinrealm.local.realm.migration.MigrationToV1
import com.example.mykotlinrealm.local.realm.migration.MigrationToV2
import io.realm.DynamicRealm
import io.realm.RealmMigration

class DefaultRealmMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        Log.d("aaa", "DefaultRealmDB: oldVersion:$oldVersion, newVersion:$newVersion")

        val schema = realm.schema
        var lastVersion = oldVersion.toInt()
        if (lastVersion == 0) {
            MigrationToV1(schema).init()
            lastVersion++
        }

        if (lastVersion == 1) {
            MigrationToV2(schema).init()
        }
    }
}
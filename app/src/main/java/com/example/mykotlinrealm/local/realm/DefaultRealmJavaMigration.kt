package com.example.mykotlinrealm.local.realm

import android.util.Log
import com.example.mykotlinrealm.local.realm.migration.MigrationToV1
import com.example.mykotlinrealm.local.realm.migration.MigrationToV2
import com.example.mykotlinrealm.local.realm.migration.MigrationToV3
import io.realm.DynamicRealm
import io.realm.RealmMigration

class DefaultRealmJavaMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        Log.d(
            "aaa", "DefaultRealmDB: oldVersion:$oldVersion, newVersion:$newVersion"
        )

        val schema = realm.schema
        var lastVersion = oldVersion.toInt()

        //migrate to v1
        if (lastVersion == 0) {
            MigrationToV1(schema).init()
            lastVersion++
        }

        //migrate to v2
        if (lastVersion == 1) {
            MigrationToV2(schema).init()
            lastVersion++
        }

        //migrate to v3
        if (lastVersion == 2) {
            MigrationToV3(schema).init()
        }
    }
}
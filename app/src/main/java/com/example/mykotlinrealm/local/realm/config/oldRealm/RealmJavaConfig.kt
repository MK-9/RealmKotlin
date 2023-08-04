package com.example.mykotlinrealm.local.realm.config.oldRealm

import android.content.Context
import com.example.mykotlinrealm.local.realm.config.RealmConfig
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmJavaConfig constructor(private val context: Context) {

    fun configRealm() {
        Realm.init(context)
        Realm.setDefaultConfiguration(createConfiguration())
    }

    private fun createConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder()
            .name(RealmConfig.FILE_NAME)
            .schemaVersion(RealmConfig.REALM_JAVA_SCHEMA_VERSION)
            .migration(DefaultRealmJavaMigration())
            .build()
    }
}
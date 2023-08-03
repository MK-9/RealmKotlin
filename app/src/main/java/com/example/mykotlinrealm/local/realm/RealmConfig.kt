package com.example.mykotlinrealm.local.realm

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmConfig constructor(private val context: Context) {

    fun init() {
        initRealm()
        configRealm()
    }

    private fun initRealm() {
        Realm.init(context)
    }

    private fun configRealm() {
        Realm.setDefaultConfiguration(getRealmConfiguration())
    }

    private fun getRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder()
            .name(FILE_NAME)
            .schemaVersion(SCHEMA_VERSION)
            .migration(DefaultRealmJavaMigration())
            .build()
    }

    companion object {
        const val FILE_NAME = "library"
        const val SCHEMA_VERSION = 3L
    }

}
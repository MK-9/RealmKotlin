package com.example.mykotlinrealm.local.realm

import android.content.Context
import com.example.mykotlinrealm.local.entity.NewBookWrapper
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmConfig constructor(private val context: Context) {

    var realmKotlin: io.realm.kotlin.Realm? = null

    fun initRealm() {
        configRealmJava()
        realmKotlin = configRealmKotlin()
    }

    private fun configRealmJava() {
        Realm.init(context)
        Realm.setDefaultConfiguration(getRealmJavaConfiguration())
    }

    private fun configRealmKotlin(): io.realm.kotlin.Realm {
        return io.realm.kotlin.Realm.open(getRealmKotlinConfiguration())
    }

    private fun getRealmJavaConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder()
            .name(FILE_NAME)
            .schemaVersion(REALM_JAVA_SCHEMA_VERSION)
            .migration(DefaultRealmJavaMigration())
            .build()
    }

    private fun getRealmKotlinConfiguration(): io.realm.kotlin.RealmConfiguration {
        return io.realm.kotlin.RealmConfiguration
            .Builder(setOf(NewBookWrapper::class))
            .name(FILE_NAME)
            .schemaVersion(REALM_KOTLIN_SCHEMA_VERSION)
            .migration(DefaultRealmKotlinConfiguration())
            .build()
    }

    companion object {
        const val FILE_NAME = "library"
        const val REALM_JAVA_SCHEMA_VERSION = 3L
        const val REALM_KOTLIN_SCHEMA_VERSION = 4L
    }

}
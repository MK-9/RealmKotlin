package com.example.mykotlinrealm.local.realm.config

import android.content.Context
import com.example.mykotlinrealm.local.realm.config.newRealm.RealmKotlinConfig
import com.example.mykotlinrealm.local.realm.config.oldRealm.RealmJavaConfig
import io.realm.kotlin.Realm

class RealmConfig constructor(private val context: Context) {

    private var realmKotlin: Realm? = null

    fun initRealm() {
        configOldRealmJava()
        configNewRealmKotlin()
    }

    private fun configOldRealmJava() {
        RealmJavaConfig(context).configRealm()
    }

    private fun configNewRealmKotlin() {
        realmKotlin = RealmKotlinConfig().configRealm()
    }

    fun getRealmKotlinInstance(): Realm? {
        return realmKotlin
    }

    companion object {
        const val FILE_NAME = "library"
        const val REALM_JAVA_SCHEMA_VERSION = 3L
        const val REALM_KOTLIN_SCHEMA_VERSION = 4L
    }

}
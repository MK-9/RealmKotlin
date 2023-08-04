package com.example.mykotlinrealm.local.realm.config.newRealm

import com.example.mykotlinrealm.local.entity.NewBookWrapper
import com.example.mykotlinrealm.local.realm.config.RealmConfig
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.TypedRealmObject
import kotlin.reflect.KClass

class RealmKotlinConfig {

    fun configRealm(): Realm {
        return Realm.open(getRealmConfiguration())
    }

    private fun getRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder(createSchemas())
            .name(RealmConfig.FILE_NAME)
            .schemaVersion(RealmConfig.REALM_KOTLIN_SCHEMA_VERSION)
            .migration(DefaultRealmKotlinConfiguration())
            .build()
    }

    private fun createSchemas(): Set<KClass<out TypedRealmObject>> {
        return setOf(NewBookWrapper::class)
    }
}
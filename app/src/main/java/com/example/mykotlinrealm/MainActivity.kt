package com.example.mykotlinrealm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Realm.init(this)
        RealmConfiguration.Builder()
            .name("library")
            .schemaVersion(1)
            .migration { realm, oldVersion, newVersion ->

                Log.d("aaa", "DefaultRealmDB: oldVersion:$oldVersion")
                Log.d("aaa", "DefaultRealmDB: newVersion:$newVersion")
                val schema = realm.schema

            }
            .build()

    }
}
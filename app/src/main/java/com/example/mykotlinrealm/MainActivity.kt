package com.example.mykotlinrealm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("library")
            .schemaVersion(0)
            .migration { realm, oldVersion, newVersion ->

                Log.d("aaa", "DefaultRealmDB: oldVersion:$oldVersion")
                Log.d("aaa", "DefaultRealmDB: newVersion:$newVersion")
                val schema = realm.schema

            }
            .build()

        Realm.setDefaultConfiguration(config)

        CoroutineScope(Dispatchers.IO).launch {
            for (account in getAccount()) {
                withContext(Dispatchers.Main) {
                    Log.d("Result: Account", "${account.title}")
                }
            }
        }
    }

    private suspend fun initAccount() {
        val accountDao = DefaultAccountDao()
        accountDao.insert(1, "a")
        accountDao.insert(2, "b")
        accountDao.insert(3, "c")
        accountDao.insert(4, "d")
        accountDao.insert(5, "e")
        accountDao.insert(6, "f")
        accountDao.insert(7, "g")
        accountDao.insert(8, "h")
    }

    private suspend fun getAccount(): List<Account> {
        val accountDao = DefaultAccountDao()
        return accountDao.getAccount()
    }


}
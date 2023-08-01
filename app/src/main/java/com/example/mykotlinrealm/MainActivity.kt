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
            .schemaVersion(1)
            .migration { realm, oldVersion, newVersion ->

                Log.d("aaa", "DefaultRealmDB: oldVersion:$oldVersion")
                Log.d("aaa", "DefaultRealmDB: newVersion:$newVersion")
                val schema = realm.schema
                if (oldVersion == 0L) {
                    schema.get(Account.ACCOUNT_CLASS)
                        ?.addField(Account.COL_SUBTITLE, String::class.java)
                }
            }
            .build()

        Realm.setDefaultConfiguration(config)

//        CoroutineScope(Dispatchers.IO).launch {
//            initAccountSchemaOne()
//        }
//
        CoroutineScope(Dispatchers.IO).launch {
            for (account in getAccount()) {
                withContext(Dispatchers.Main) {
                    Log.d("Result: Account", "${account.title}")
                }
            }
        }


    }

    private suspend fun initAccountSchemaZero() {
        val accountDao = DefaultAccountDao()
        accountDao.insert(1, "a", "aa")
        accountDao.insert(2, "b", "bb")
        accountDao.insert(3, "c", "cc")
        accountDao.insert(4, "d", "dd")
        accountDao.insert(5, "e", "ee")
        accountDao.insert(6, "f", "ff")
        accountDao.insert(7, "g", "gg")
        accountDao.insert(8, "h", "hh")
    }


    private suspend fun initAccountSchemaOne() {
        val accountDao = DefaultAccountDao()
        accountDao.insert(9, "i", "ii")
        accountDao.insert(10, "j", "jj")
        accountDao.insert(11, "k", "kk")
        accountDao.insert(12, "l", "ll")
        accountDao.insert(13, "m", "mm")
        accountDao.insert(14, "n", "nn")
        accountDao.insert(15, "o", "oo")
        accountDao.insert(16, "p", "pp")
    }

    private suspend fun getAccount(): List<Account> {
        val accountDao = DefaultAccountDao()
        return accountDao.getAccount()
    }


}
package com.example.mykotlinrealm

import io.realm.Realm
import io.realm.RealmResults
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DefaultAccountDao : AccountDao {

    private val mutex = Mutex()

    override suspend fun insert(userId: Int, title: String) {
        val realm = Realm.getDefaultInstance()
        mutex.withLock {
            try {
                realm.beginTransaction()
                val account = Account(getCount(realm).toInt(), userId, title)
                realm.insert(account)
                realm.commitTransaction()
            } catch (e: Exception) {
                realm.cancelTransaction()
            } finally {
                realm.close()
            }
        }
    }

    private fun getCount(realm: Realm): Long {
        val number: Number? = realm.where(Account::class.java).max(Account.COL_ID)
        return (number?.toLong() ?: 0) + 1
    }

    override suspend fun getAccount(): List<Account> {
        val realm = Realm.getDefaultInstance()
        mutex.withLock {
            try {
                val accounts: RealmResults<Account> =
                    realm.where(Account::class.java).findAll()
                return realm.copyFromRealm(accounts)
            } catch (e: Exception) {
                return arrayListOf()
            } finally {
                realm.close()
            }
        }
    }
}
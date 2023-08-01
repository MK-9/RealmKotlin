package com.example.mykotlinrealm.local.dao

import com.example.mykotlinrealm.local.entity.BookWrapper
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DefaultBookWrapperDao : BookWrapperDao {

    private val mutex = Mutex()

    override suspend fun insertBook(title: String, description: String) {
        val realm = Realm.getDefaultInstance()
        mutex.withLock {
            try {
                realm.beginTransaction()
                val id = getCount(realm).toInt()
                val book = BookWrapper(
                    id,
                    title,
                    description
                )
                realm.insert(book)
                realm.commitTransaction()
            } catch (e: Exception) {
                realm.cancelTransaction()
            } finally {
                realm.close()
            }
        }
    }

    private fun getCount(realm: Realm): Long {
        val number: Number? = realm.where(BookWrapper::class.java).max(BookWrapper.COL_ID)
        return (number?.toLong() ?: 0) + 1
    }

    override suspend fun getBooks(): List<BookWrapper> {
        val realm = Realm.getDefaultInstance()
        mutex.withLock {
            try {
                val books: RealmResults<BookWrapper> =
                    realm.where(BookWrapper::class.java).findAll()
                return realm.copyFromRealm(books)
            } catch (e: Exception) {
                return arrayListOf()
            } finally {
                realm.close()
            }
        }
    }
}
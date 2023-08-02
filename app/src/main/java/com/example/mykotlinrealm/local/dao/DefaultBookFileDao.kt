package com.example.mykotlinrealm.local.dao

import com.example.mykotlinrealm.local.entity.BookFile
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DefaultBookFileDao : BookFileDao {
    private val mutex = Mutex()

    override suspend fun insertBookFile(title: String, storagePath: String) {
        val realm = Realm.getDefaultInstance()
        mutex.withLock {
            try {
                realm.beginTransaction()
                val id = getCount(realm).toInt()
                val file = BookFile(
                    id,
                    title,
                    storagePath
                )
                realm.insert(file)
                realm.commitTransaction()
            } catch (e: Exception) {
                realm.cancelTransaction()
            } finally {
                realm.close()
            }
        }
    }

    private fun getCount(realm: Realm): Long {
        val number: Number? = realm.where(BookFile::class.java).max(BookFile.COL_ID)
        return (number?.toLong() ?: 0) + 1
    }

    override suspend fun getBookFiles(): List<BookFile> {
        val realm = Realm.getDefaultInstance()
        mutex.withLock {
            try {
                val files: RealmResults<BookFile> =
                    realm.where(BookFile::class.java).findAll()
                return realm.copyFromRealm(files)
            } catch (e: Exception) {
                return arrayListOf()
            } finally {
                realm.close()
            }
        }
    }
}
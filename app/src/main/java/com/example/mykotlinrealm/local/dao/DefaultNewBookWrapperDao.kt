package com.example.mykotlinrealm.local.dao

import com.example.mykotlinrealm.local.entity.NewBookWrapper
import io.realm.kotlin.Realm
import io.realm.kotlin.query.RealmResults

class DefaultNewBookWrapperDao constructor(val realm: Realm?) : NewBookWrapperDao {

    override suspend fun insertBook(title: String, description: String) {
        realm?.write {
            val book = NewBookWrapper().apply {
                id = getCount(realm).toInt()
                this.title = title
                this.description = description
            }
            copyToRealm(book)
        }
    }

    private fun getCount(realm: Realm): Long {
        val count: Long? = realm
            .query(NewBookWrapper::class)
            .max(NewBookWrapper.COL_ID, Long::class)
            .find()
        return count?.run { this + 1 } ?: 0
    }

    override suspend fun getBooks(): List<NewBookWrapper> {
        val books: RealmResults<NewBookWrapper>? =
            realm?.query(NewBookWrapper::class)?.find()

        return books?.run {
            return if (size == 0) arrayListOf()
            else subList(0, size - 1)
        } ?: arrayListOf()
    }
}
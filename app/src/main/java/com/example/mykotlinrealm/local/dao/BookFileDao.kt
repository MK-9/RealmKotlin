package com.example.mykotlinrealm.local.dao

import com.example.mykotlinrealm.local.entity.BookFile

interface BookFileDao {
    suspend fun insertBookFile(title: String, storagePath: String)
    suspend fun getBookFiles(): List<BookFile>
}
package com.example.mykotlinrealm.local.dao

import com.example.mykotlinrealm.local.entity.BookWrapper

interface BookWrapperDao {
    suspend fun insertBook(title: String, description: String)
    suspend fun getBooks(): List<BookWrapper>
}
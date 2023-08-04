package com.example.mykotlinrealm.local.dao

import com.example.mykotlinrealm.local.entity.NewBookWrapper

interface NewBookWrapperDao {
    suspend fun insertBook(title: String, description: String)
    suspend fun getBooks(): List<NewBookWrapper>
}
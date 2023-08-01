package com.example.mykotlinrealm.local.dao

import com.example.mykotlinrealm.local.entity.Account

interface AccountDao {
    suspend fun insert(userId:Int , title:String, subtitle:String)
    suspend fun getAccount(): List<Account>
}
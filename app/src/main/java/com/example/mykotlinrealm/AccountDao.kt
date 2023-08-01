package com.example.mykotlinrealm

interface AccountDao {
    suspend fun insert(userId:Int , title:String, subtitle:String)
    suspend fun getAccount(): List<Account>
}
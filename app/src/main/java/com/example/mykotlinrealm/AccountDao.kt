package com.example.mykotlinrealm

interface AccountDao {
    suspend fun insert(userId:Int , title:String)
    suspend fun getAccount(): List<Account>
}
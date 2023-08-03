package com.example.mykotlinrealm.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mykotlinrealm.R
import com.example.mykotlinrealm.local.dao.DefaultAccountDao
import com.example.mykotlinrealm.local.dao.DefaultBookFileDao
import com.example.mykotlinrealm.local.dao.DefaultBookWrapperDao
import com.example.mykotlinrealm.local.entity.Account
import com.example.mykotlinrealm.local.entity.BookFile
import com.example.mykotlinrealm.local.entity.BookWrapper
import com.example.mykotlinrealm.local.realm.RealmConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val realmConfig = RealmConfig(this)
        realmConfig.init()

        CoroutineScope(Dispatchers.IO).launch {
            initAccountSchemaOne()
            initBookSchemaTwo()
            initBookSchemaThree()
        }

//        CoroutineScope(Dispatchers.IO).launch {
//            for (account in getAccounts()) {
//                withContext(Dispatchers.Main) {
//                    Log.d("Result: Account", "${account.title}")
//                }
//            }
//        }

//        CoroutineScope(Dispatchers.IO).launch {
//            for (book in getBooks()) {
//                withContext(Dispatchers.Main) {
//                    Log.d("Result: Book", "${book.description}")
//                }
//            }
//        }

        CoroutineScope(Dispatchers.IO).launch {
            for (book in getBookFiles()) {
                withContext(Dispatchers.Main) {
                    Log.d("Result: BookFile", "${book.storagePath}")
                }
            }
        }

    }

    private suspend fun initAccountSchemaZero() {
        val accountDao = DefaultAccountDao()
        accountDao.insert(1, "a", "aa")
        accountDao.insert(2, "b", "bb")
        accountDao.insert(3, "c", "cc")
        accountDao.insert(4, "d", "dd")
        accountDao.insert(5, "e", "ee")
        accountDao.insert(6, "f", "ff")
        accountDao.insert(7, "g", "gg")
        accountDao.insert(8, "h", "hh")
    }


    private suspend fun initAccountSchemaOne() {
        val accountDao = DefaultAccountDao()
        accountDao.insert(9, "i", "ii")
        accountDao.insert(10, "j", "jj")
        accountDao.insert(11, "k", "kk")
        accountDao.insert(12, "l", "ll")
        accountDao.insert(13, "m", "mm")
        accountDao.insert(14, "n", "nn")
        accountDao.insert(15, "o", "oo")
        accountDao.insert(16, "p", "pp")
    }

    private suspend fun initBookSchemaTwo() {
        val bookDao = DefaultBookWrapperDao()
        bookDao.insertBook("a", "aaaaaa")
        bookDao.insertBook("b", "bbbbbb")
        bookDao.insertBook("c", "cccccc")
        bookDao.insertBook("d", "dddddd")
        bookDao.insertBook("e", "eeeeee")
        bookDao.insertBook("f", "ffffff")
        bookDao.insertBook("g", "gggggg")
    }

    private suspend fun initBookSchemaThree() {
        val bookDao = DefaultBookFileDao()
        bookDao.insertBookFile("a", "C:\\Users\\Mking\\OneDrive\\Desktop\\aaaaa.realm")
        bookDao.insertBookFile("b", "C:\\Users\\Mking\\OneDrive\\Desktop\\bbbbbb.realm")
        bookDao.insertBookFile("c", "C:\\Users\\Mking\\OneDrive\\Desktop\\cccccc.realm ")
        bookDao.insertBookFile("d", "C:\\Users\\Mking\\OneDrive\\Desktop\\dddddd.realm")
        bookDao.insertBookFile("e", "C:\\Users\\Mking\\OneDrive\\Desktop\\eeeeee.realm")
        bookDao.insertBookFile("f", "C:\\Users\\Mking\\OneDrive\\Desktop\\ffffff.realm")
        bookDao.insertBookFile("g", "C:\\Users\\Mking\\OneDrive\\Desktop\\gggggg.realm")
    }


    private suspend fun getAccounts(): List<Account> {
        val accountDao = DefaultAccountDao()
        return accountDao.getAccount()
    }

    private suspend fun getBooks(): List<BookWrapper> {
        val bookDao = DefaultBookWrapperDao()
        return bookDao.getBooks()
    }

    private suspend fun getBookFiles(): List<BookFile> {
        val bookFileDao = DefaultBookFileDao()
        return bookFileDao.getBookFiles()
    }


}
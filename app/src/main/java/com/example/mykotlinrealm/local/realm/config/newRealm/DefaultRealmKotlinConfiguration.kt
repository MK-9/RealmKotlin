package com.example.mykotlinrealm.local.realm.config.newRealm

import com.example.mykotlinrealm.local.entity.BookWrapper
import com.example.mykotlinrealm.local.entity.NewBookWrapper
import io.realm.kotlin.dynamic.DynamicMutableRealmObject
import io.realm.kotlin.dynamic.DynamicRealmObject
import io.realm.kotlin.migration.AutomaticSchemaMigration

class DefaultRealmKotlinConfiguration : AutomaticSchemaMigration {

    override fun migrate(migrationContext: AutomaticSchemaMigration.MigrationContext) {
        val oldRealm = migrationContext.oldRealm
        val newRealm = migrationContext.newRealm

        val schemaVersion = oldRealm.schemaVersion()
        var oldVersion: Int = schemaVersion.toInt()

        if (oldVersion == 3) {
            val oldBooks = oldRealm.query(BookWrapper.BOOK_WRAPPER_CLASS).find()
            for (book in oldBooks) {
                val id = book.getValue(BookWrapper.COL_ID, Long::class)
                val title = book.getNullableValue(BookWrapper.COL_TITLE, String::class)
                val description = book.getNullableValue(BookWrapper.COL_DESCRIPTION, String::class)

                val dynamicRealmObject =
                    DynamicMutableRealmObject.create(
                        type = NewBookWrapper.BOOK_WRAPPER_CLASS,
                        properties = mapOf(
                            NewBookWrapper.COL_ID to id,
                            NewBookWrapper.COL_TITLE to title,
                            NewBookWrapper.COL_DESCRIPTION to description
                        )
                    )
                newRealm.copyToRealm(dynamicRealmObject)
            }
        }


//        migrationContext.enumerate(BookWrapper.BOOK_WRAPPER_CLASS) { oldObject: DynamicRealmObject,
//                                                                     newObject: DynamicMutableRealmObject? ->
//            Log.d("Result book:", "title")
//        }
//        if (oldVersion == 6L) {
//            migrationContext
//                .enumerate(BookWrapper.BOOK_WRAPPER_CLASS, ::migrateBookWrapperInV3)
//            oldVersion++
//        }
//
//        if (oldVersion == 7L) {
//
//        }
    }


    private fun migrateBookWrapperInV3(
        oldObject: DynamicRealmObject,
        newObject: DynamicMutableRealmObject?
    ) {
        newObject?.run {
            // Merge property
            set(
                NewBookWrapper.COL_ID,
                oldObject.getValue(BookWrapper.COL_ID, Int::class)
            )

            set(
                NewBookWrapper.COL_TITLE,
                oldObject.getValue(BookWrapper.COL_TITLE, String::class)
            )

            set(
                NewBookWrapper.COL_DESCRIPTION,
                oldObject.getValue(BookWrapper.COL_DESCRIPTION, String::class)
            )
        }
    }

}
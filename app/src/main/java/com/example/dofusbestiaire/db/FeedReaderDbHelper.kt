package com.example.dofusbestiaire.db
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import  android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object FeedReaderContract {
    // Table contents are grouped together in an anonymous object.
    object FeedEntry : BaseColumns {
        const val TABLE_NAME = "favorite"
        const val COLUMN_NAME_BESTIAIRE_ID = "bestiaireId"
    }
}
class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${FeedReaderContract.FeedEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FeedReaderContract.FeedEntry.COLUMN_NAME_BESTIAIRE_ID} INTEGER)"

    val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedReaderContract.FeedEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "DofusBestiaire"
    }
}
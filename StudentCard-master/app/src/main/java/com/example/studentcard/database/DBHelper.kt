package com.example.studentcard.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * The Class manages the Database.
 */
class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    /**
     * This Method creates the Database.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
            CREATE TABLE ToDo (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                priority STRING NOT NULL,
                deadline TEXT NOT NULL,
                description TEXT,
                status INTEGER NOT NULL DEFAULT 0
            )
            """
        )
    }

    /**
     * This Method updates the Database.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ToDo")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "todo_Database.db"
        const val DATABASE_VERSION = 1
    }
}
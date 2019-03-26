package com.tistory.jeongs0222.kagongapplication.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USERINFO (googlekey TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertUser(googlekey: String) {
        val db = writableDatabase

        db.execSQL("INSERT INTO USERINFO VALUES('$googlekey');")

        db.close()
    }

    fun getGooglekey(): String {
        var key = ""

        val db = readableDatabase

        val cursor = db.rawQuery("SELECT googlekey FROM USERINFO", null)

        while(cursor.moveToNext()) {
            key = cursor.getString(0)
        }

        cursor.close()

        db.close()

        return key
    }

    fun deleteGooglekey() {
        val db = writableDatabase

        db.execSQL("DELETE FROM USERINFO")

        db.close()
    }

}
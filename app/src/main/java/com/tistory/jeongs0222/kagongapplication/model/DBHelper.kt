package com.tistory.jeongs0222.kagongapplication.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USERINFO (userkey TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertUser(userkey: String) {
        val db = writableDatabase

        db.execSQL("INSERT INTO USERINFO VALUES('$userkey');")

        db.close()
    }

    fun getUserkey(): String {
        var key = ""

        val db = readableDatabase

        val cursor = db.rawQuery("SELECT userkey FROM USERINFO", null)

        while(cursor.moveToNext()) {
            key = cursor.getString(0)
        }

        cursor.close()

        db.close()

        return key
    }

}
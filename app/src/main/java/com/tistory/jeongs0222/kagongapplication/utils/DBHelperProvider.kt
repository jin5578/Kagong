package com.tistory.jeongs0222.kagongapplication.utils

import android.content.Context
import com.tistory.jeongs0222.kagongapplication.model.DBHelper


interface DBHelperProvider {

    fun getDBHelper(): DBHelper

}

class DBHelperProviderImpl(private val context: Context): DBHelperProvider {

    override fun getDBHelper(): DBHelper = DBHelper(context, "USERINFO.db", null, 1)


}
package com.tistory.jeongs0222.kagongapplication.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class DateFormatter {
    val CALENDAR_HEADER_FORMAT = "MM월"
    val CALENDAR_DAY_FORMAT = "d"

    fun getDate(date: Long, pattern: String): String {
        return try {
            val formatter = SimpleDateFormat(pattern, Locale.KOREA)
            val d = Date(date)

            formatter.format(d).toUpperCase()
        } catch (e: Exception) {
            e.printStackTrace()

            ""
        }
    }

    /*fun getDate(date: Long, pattern: String): String {
        try {
            val formatter = SimpleDateFormat(pattern, Locale.ENGLISH)
            val d = Date(date)
            return formatter.format(d).toUpperCase()
        } catch (e: Exception) {
            return " "
        }

    }*/
}
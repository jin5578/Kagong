package com.tistory.jeongs0222.kagongapplication.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class DateFormatter {
    val CALENDAR_FULL_FORMAT = "yyyy.MM.dd"
    val CALENDAR_FORMAT = "MM.dd"
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
}
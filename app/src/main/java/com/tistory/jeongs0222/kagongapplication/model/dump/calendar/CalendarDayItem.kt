package com.tistory.jeongs0222.kagongapplication.model.dump.calendar

import java.util.*


data class CalendarDayItem(var calendar: String,
                           var position: Int,
                           var gregorianCalendar: GregorianCalendar
)
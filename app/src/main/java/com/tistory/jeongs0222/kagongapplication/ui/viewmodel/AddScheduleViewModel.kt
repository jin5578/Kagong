package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.kagongapplication.model.dump.calendar.CalendarResult
import java.text.SimpleDateFormat
import java.util.*
import android.R
import android.R.attr.colorPrimaryDark




class AddScheduleViewModel: DisposableViewModel(), AddScheduleEventListener {

    private val _calendarList = MutableLiveData<MutableList<CalendarResult>>()
    val calendarList: LiveData<MutableList<CalendarResult>>
        get() = _calendarList

    private val HEADER_TYPE = "HEADER"
    private val EMPTY_TYPE = "EMPTY"
    private val DAY_TYPE = "DAY"


    init {

        setCalendarList()

    }

    private fun setCalendarList() {


    }

    override fun clickEvent() {

    }

}

interface AddScheduleEventListener {

    fun clickEvent()

}
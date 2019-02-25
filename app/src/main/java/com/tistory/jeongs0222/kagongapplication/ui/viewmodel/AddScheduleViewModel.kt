package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*


class AddScheduleViewModel : DisposableViewModel(), AddScheduleEventListener {

    private val _calendarList = MutableLiveData<MutableList<Any>>()
    val calendarList: LiveData<MutableList<Any>>
        get() = _calendarList

    private val TAG = "AddScheduleViewModel"

    var mCenterPosition: Int = 0


    init {
        setCalendarList()
    }

    private fun setCalendarList() {
        val cal = GregorianCalendar()

        val calendarList = ArrayList<Any>()

        for(i in -1.. 12) {
            try {
                val calendar = GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0)

                if(i == 0) {
                    mCenterPosition = calendarList.size
                }

                calendarList.add(calendar.timeInMillis)

                val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
                val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                for(j in 0 until dayOfWeek) {
                    calendarList.add("empty")
                }

                for(j in 1.. max) {
                    calendarList.add(GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j))
                }
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
        _calendarList.value = calendarList
    }

    override fun clickEvent() {

    }

}

interface AddScheduleEventListener {

    fun clickEvent()

}
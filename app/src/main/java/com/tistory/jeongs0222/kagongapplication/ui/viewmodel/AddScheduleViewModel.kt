package com.tistory.jeongs0222.kagongapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*


class AddScheduleViewModel : DisposableViewModel(), AddScheduleEventListener {

    private val _calendarList = MutableLiveData<MutableList<Any>>()
    val calendarList: LiveData<MutableList<Any>>
        get() = _calendarList

    private val _startPosition = MutableLiveData<Int>()
    val startPosition: LiveData<Int>
        get() = _startPosition

    private val _endPosition = MutableLiveData<Int>()
    val endPosition: LiveData<Int>
        get() = _endPosition

    private val _positionChange = MutableLiveData<Int>()
    val positionChange: LiveData<Int>
        get() = _positionChange


    private val TAG = "AddScheduleViewModel"

    var mCenterPosition: Int = 0

    var lastStartPosition = 0
    var lastEndPosition = 0

    init {
        setCalendarList()

        _startPosition.value = 0
        _endPosition.value = 0
    }

    private fun setCalendarList() {
        val cal = GregorianCalendar()

        val calendarList = ArrayList<Any>()

        for (i in -1..12) {
            try {
                val calendar = GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0)

                if (i == 0) {
                    mCenterPosition = calendarList.size
                }

                calendarList.add(calendar.timeInMillis)

                val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
                val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                for (j in 0 until dayOfWeek) {
                    calendarList.add("empty")
                }

                for (j in 1..max) {
                    calendarList.add(GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        _calendarList.value = calendarList
    }

    override fun dayClickEvent(day: String, position: Int) {
        when {
            _startPosition.value == 0 -> //시작 값이 없는 경우
                _startPosition.value = position

            _endPosition.value == 0 -> //시작 값은 있지만 끝 값이 없는 경우

                when {
                    position < _startPosition.value!! -> {     //시작 값이 있고 끝 값에 넣어야 하는 경우 중에 선택된 값이 시작 값 보다 작은 경우
                        _endPosition.value = _startPosition.value
                        _startPosition.value = position
                    }

                    position == _startPosition.value!! -> //시작 값이 있고 끝 값에 넣어야 하는 경우 중에 선택된 값이 시작 값과 같은 경우
                        Log.e(TAG, "position == _startPosition.value")

                    else -> //시작 값이 있고 끝 값에 넣어야 하는 경우 중에 시작 값 보다 작거나 같지 않은 경우
                        _endPosition.value = position
                }

            else -> {
                lastEndPosition = _endPosition.value!!
                _positionChange.value = 1

                lastStartPosition = _startPosition.value!!
                _positionChange.value = 0

                _startPosition.value = position
                _endPosition.value = 0
            }
        }

        Log.e("DayItemClick", "day : " + day + " position : " + position.toString())
    }

}

interface AddScheduleEventListener {
    fun dayClickEvent(day: String, position: Int)
}
package com.tistory.jeongs0222.kagongapplication.ui.viewmodel




class AddScheduleViewModel: DisposableViewModel(), AddScheduleEventListener {


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
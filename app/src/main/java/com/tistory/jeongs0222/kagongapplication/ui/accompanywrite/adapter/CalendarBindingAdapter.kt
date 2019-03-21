package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.adapter

import android.view.View
import android.widget.CalendarView
import androidx.databinding.BindingAdapter


@BindingAdapter("calendarVisibility")
fun calendarVisibility(calendarView: CalendarView, sort: Int) {
    when(sort) {
        0 ->
            calendarView.visibility = View.VISIBLE
        else ->
            calendarView.visibility = View.GONE
    }
}


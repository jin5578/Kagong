package com.tistory.jeongs0222.kagongapplication.ui.addschedule.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("addCalendarList")
fun calendarList(recyclerView: RecyclerView, list: MutableList<Any>?) {
    if(list != null) {
        (recyclerView.adapter as CalendarListAdapter).apply {
            submitList(list)
        }
    }
}
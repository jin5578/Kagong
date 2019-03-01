package com.tistory.jeongs0222.kagongapplication.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResult


@BindingAdapter("myScheduleList")
fun bringScheduleBindingAdapter(recyclerView: RecyclerView, list: MutableList<BringScheduleResult>?) {
    if (list != null) {
        (recyclerView.adapter as BringScheduleAdapter).apply {
            submitList(list)
        }
    }
}
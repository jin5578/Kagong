package com.tistory.jeongs0222.kagongapplication.ui.view.adddetailschedule.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.bringDetailSchedule.BringDetailScheduleResult


@BindingAdapter("detailScheduleList")
fun detailScheduleList(recyclerView: RecyclerView, list: MutableList<BringDetailScheduleResult>?) {
    if (list != null) {
        (recyclerView.adapter as BringDetailScheduleAdapter).apply {
            submitList(list)
        }
    }
}

package com.tistory.jeongs0222.kagongapplication.ui.userprofile.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.bringTOPSchedule.BringTOPScheduleResult


@BindingAdapter("topItemList")
fun topItemList(recyclerView: RecyclerView, list: MutableList<BringTOPScheduleResult>?) {
    if(list != null) {
        (recyclerView.adapter as BringTOPScheduleAdapter).apply {
            submitList(list)
        }
    }
}




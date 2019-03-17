package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResult


@BindingAdapter("areaLocationList")
fun areaLocationList(recyclerView: RecyclerView, list: MutableList<BringAreaLocationResult>?) {
    if(list != null) {
        (recyclerView.adapter as BringAreaLocationAdapter).apply {
            submitList(list)
        }
    }
}

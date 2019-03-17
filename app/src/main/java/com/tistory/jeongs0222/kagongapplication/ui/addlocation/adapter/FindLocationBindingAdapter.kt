package com.tistory.jeongs0222.kagongapplication.ui.addlocation.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaLocation.BringAreaLocationResult


@BindingAdapter("addFindLocationList")
fun findLocationList(recyclerView: RecyclerView, list: MutableList<BringAreaLocationResult>?) {
    if(list != null) {
        (recyclerView.adapter as FindLocationAdapter).apply {
            submitList(list)
        }
    }
}
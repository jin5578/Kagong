package com.tistory.jeongs0222.kagongapplication.ui.locationdetail.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.bringOperatingTime.BringOperatingTimeResult


@BindingAdapter("operatingTimeItem")
fun operatingTimeItem(recyclerView: RecyclerView, item: MutableList<BringOperatingTimeResult>?) {
    if(item != null) {
        (recyclerView.adapter as OperatingTimeAdapter).apply {
            submitList(item)
        }
    }
}

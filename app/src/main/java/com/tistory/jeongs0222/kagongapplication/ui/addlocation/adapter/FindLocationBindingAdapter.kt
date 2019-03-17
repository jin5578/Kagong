package com.tistory.jeongs0222.kagongapplication.ui.addlocation.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.findLocation.FindLocationResult


@BindingAdapter("addFindLocationList")
fun findLocationList(recyclerView: RecyclerView, list: MutableList<FindLocationResult>?) {
    if(list != null) {
        (recyclerView.adapter as FindLocationAdapter).apply {
            submitList(list)
        }
    }
}
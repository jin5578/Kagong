package com.tistory.jeongs0222.kagongapplication.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.findarea.FindAreaResult


@BindingAdapter("addFindAreaList")
fun findList(recyclerView: RecyclerView, list: MutableList<FindAreaResult>?) {
    if(list != null) {
        (recyclerView.adapter as FindAreaAdapter).apply {
            submitList(list)
        }
    }
}
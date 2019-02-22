package com.tistory.jeongs0222.kagongapplication.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.accompanylist.AccompanyListResult


@BindingAdapter("addAccompanyList")
fun accompanyList(recyclerView: RecyclerView, list: MutableList<AccompanyListResult>?) {
    if(list != null) {
        (recyclerView.adapter as AccompanyListAdapter).apply {
            submitList(list)
        }
    }
}
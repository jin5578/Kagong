package com.tistory.jeongs0222.kagongapplication.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.areasearch.AreaSearchResult


@BindingAdapter("addList")
fun searchList(recyclerView: RecyclerView, list: MutableList<AreaSearchResult>?) {
    if(list != null)
        (recyclerView.adapter as AreaSearchHistoryAdapter).apply {
            submitList(list)
        }
}
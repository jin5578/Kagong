package com.tistory.jeongs0222.kagongapplication.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.year.YearItem


@BindingAdapter("addYearList")
fun yearList(recyclerView: RecyclerView, list: MutableList<YearItem>?) {
    if(list != null)
        (recyclerView.adapter as YearAdapter).apply {
            submitList(list)
        }
}
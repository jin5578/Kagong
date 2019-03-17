package com.tistory.jeongs0222.kagongapplication.ui.view.register.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.dump.year.YearItem


@BindingAdapter("addYearList")
fun yearList(recyclerView: RecyclerView, list: MutableList<YearItem>?) {
    if(list != null)
        (recyclerView.adapter as YearAdapter).apply {
            submitList(list)
        }
}
package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.bringAccompany.BringAccompanyResult


@BindingAdapter("accompanyList")
fun accompanyList(recyclerView: RecyclerView, list: MutableList<BringAccompanyResult>?) {
    if(list != null) {
        (recyclerView.adapter as BringAccompanyAdapter).apply {
            submitList(list)
        }
    }
}
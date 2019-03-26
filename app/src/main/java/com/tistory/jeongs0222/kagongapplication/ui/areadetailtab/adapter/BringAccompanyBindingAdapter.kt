package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter

import android.view.View
import android.widget.TextView
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

@BindingAdapter("accompanyNullVisibility")
fun accompanyNullVisibility(textView: TextView, list: MutableList<BringAccompanyResult>?) {
    if(list != null) {
        if(list.size == 0) {
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }
}
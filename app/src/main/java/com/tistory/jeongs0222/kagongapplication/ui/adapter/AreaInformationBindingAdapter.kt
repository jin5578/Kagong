package com.tistory.jeongs0222.kagongapplication.ui.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResult


@BindingAdapter("addInformationList")
fun informationList(recyclerView: RecyclerView, list: MutableList<AreaInformationResult>?) {
    if(list != null) {
        (recyclerView.adapter as AreaInformationAdapter).apply {
            submitList(list)
        }
    }
}
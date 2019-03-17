package com.tistory.jeongs0222.kagongapplication.ui.view.areadetailtab.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaGoodPlace.BringAreaGoodPlaceResult


@BindingAdapter("areaLocationList")
fun areaLocationList(recyclerView: RecyclerView, list: MutableList<BringAreaGoodPlaceResult>?) {
    if(list != null) {
        (recyclerView.adapter as BringAreaGoodPlaceAdapter).apply {
            submitList(list)
        }
    }
}

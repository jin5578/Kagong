package com.tistory.jeongs0222.kagongapplication.ui.view.addlocation.adapter

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("confirmVisibility")
fun confirmVisibility(view: ImageView, sort: Boolean) {
    if(sort) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("frameLayoutVisibility")
fun frameLayoutVisibility(view: FrameLayout, sort: Boolean) {
    if(sort) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
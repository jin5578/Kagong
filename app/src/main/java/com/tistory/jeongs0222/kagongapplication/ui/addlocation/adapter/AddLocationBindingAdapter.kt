package com.tistory.jeongs0222.kagongapplication.ui.addlocation.adapter

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.github.ybq.android.spinkit.SpinKitView


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

@BindingAdapter("writeClickable")
fun writeClickable(imageView: ImageView, bool: Boolean?) {
    if(bool != null) {
        when(bool) {
            true ->
                imageView.isClickable = true

            else ->
                imageView.isClickable = false
        }
    }
}

@BindingAdapter("pVisibility")
fun pVisibility(spinKitView: SpinKitView, bool: Boolean?) {
    if(bool != null) {
        when(bool) {
            true ->
                spinKitView.visibility = View.VISIBLE

            else ->
                spinKitView.visibility = View.GONE
        }
    }
}
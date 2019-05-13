package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.github.ybq.android.spinkit.SpinKitView


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

@BindingAdapter("progressbarVisibility")
fun progressbarVisibility(spinKitView: SpinKitView, bool: Boolean?) {
    if(bool != null) {
        when(bool) {
            true ->
                spinKitView.visibility = View.VISIBLE

            else ->
                spinKitView.visibility = View.GONE
        }
    }
}
package com.tistory.jeongs0222.kagongapplication.ui.review.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("rWriteClickable")
fun rWriteClickable(imageView: ImageView, bool: Boolean?) {
    if(bool != null) {
        when(bool) {
            true ->
                imageView.isClickable = true

            else ->
                imageView.isClickable = false
        }
    }
}

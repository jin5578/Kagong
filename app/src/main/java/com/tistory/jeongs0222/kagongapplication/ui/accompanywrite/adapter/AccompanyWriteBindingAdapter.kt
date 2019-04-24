package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter


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
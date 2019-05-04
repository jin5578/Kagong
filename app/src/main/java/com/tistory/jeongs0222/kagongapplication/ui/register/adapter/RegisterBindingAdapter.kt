package com.tistory.jeongs0222.kagongapplication.ui.register.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("femaleCheckVisibility")
fun femaleCheckVisibility(imageView: ImageView, userSex: String?) {
    if(userSex != null) {
        when(userSex) {
            "female" -> imageView.visibility = View.VISIBLE

            else -> imageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("maleCheckVisibility")
fun maleCheckVisibility(imageView: ImageView, userSex: String?) {
    if(userSex != null) {
        when(userSex) {
            "male" -> imageView.visibility = View.VISIBLE

            else -> imageView.visibility = View.GONE
        }
    }
}
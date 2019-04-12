package com.tistory.jeongs0222.kagongapplication.ui.addschedule.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("bothSelectedVisibility")
fun bothSelectedVisibility(textView: TextView, status: Boolean?) {
    if(status != null) {
        when(status) {
            true ->
                textView.visibility = View.VISIBLE

            false ->
                textView.visibility = View.GONE
        }
    }
}
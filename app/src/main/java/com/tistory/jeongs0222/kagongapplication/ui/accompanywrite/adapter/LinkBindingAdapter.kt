package com.tistory.jeongs0222.kagongapplication.ui.accompanywrite.adapter

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter


@BindingAdapter("linkVisibility")
fun linkVisibility(editText: EditText, sort: Int) {
    when(sort) {
        0 ->
            editText.visibility = View.VISIBLE
        else ->
            editText.visibility = View.GONE
    }
}
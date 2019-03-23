package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.annotation.SuppressLint
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter


@SuppressLint("SetTextI18n")
@BindingAdapter("homeSearchText")
fun homeSearchText(textView: TextView, nickname: String?) {
    if(nickname != null) {
        textView.text = nickname + "님 어디로 떠나시나요?"
    }
}

@BindingAdapter("searchText")
fun searchText(editText: EditText, nickname: String?) {
    if(nickname != null) {
        editText.hint = nickname + "님 어디로 떠나시나요?"
    }
}

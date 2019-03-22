package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter


@SuppressLint("SetTextI18n")
@BindingAdapter("nicknameText")
fun nicknameText(textView: TextView, nickname: String) {
    textView.text = nickname + "님\n" +
            "안녕하세요."
}

@BindingAdapter("introduceText")
fun introduceText(textView: TextView, introduce: String?) {
    if(introduce == null) {
        textView.text = "회원님에 대해 알 수 있도록 간략하게 자기소개를 해주세요."
    } else {
        textView.text = introduce
    }

}
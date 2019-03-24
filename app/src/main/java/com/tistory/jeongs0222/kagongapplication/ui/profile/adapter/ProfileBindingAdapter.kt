package com.tistory.jeongs0222.kagongapplication.ui.profile.adapter

import android.annotation.SuppressLint
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter


@SuppressLint("SetTextI18n")
@BindingAdapter("nicknameText")
fun nicknameText(textView: TextView, nickname: String?) {
    if(nickname != null) {
        textView.text = nickname + "님"
    }
}

@BindingAdapter("introduceText")
fun introduceText(textView: TextView, introduce: String?) {
    if (introduce == "") {
        textView.text = "회원님에 대해 알 수 있도록 간략하게 자기소개를 해주세요."
    } else {
        textView.text = introduce
    }
}

@BindingAdapter("introduceHint")
fun introduceHint(editText: EditText, introduce: String?) {
    if(introduce == "") {
        editText.hint = "회원님에 대해 알 수 있도록 간략하게 자기소개를 해주세요."
    } else {
        editText.setText(introduce)
    }
}

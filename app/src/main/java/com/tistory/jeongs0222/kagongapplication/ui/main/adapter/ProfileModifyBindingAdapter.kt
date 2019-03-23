package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.widget.EditText
import androidx.databinding.BindingAdapter


@BindingAdapter("introduceHint")
fun introduceHint(editText: EditText, introduce: String?) {
    if(introduce == "") {
        editText.hint = "회원님에 대해 알 수 있도록 간략하게 자기소개를 해주세요."
    } else {
        editText.setText(introduce)
    }
}

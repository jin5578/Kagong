package com.tistory.jeongs0222.kagongapplication.ui.view.fragment.areadetailtabfragment

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("hintText")
fun hintText(textView: TextView, sort: Int?) {
    if (sort != null) {
        when(sort) {
            1 -> textView.hint = "관광지를 검색해보세요."

            2 -> textView.hint = "맛집을 검색해보세요."
        }
    }
}

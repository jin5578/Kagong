package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

@SuppressLint("SetTextI18n")
@BindingAdapter("tagAreaText")
fun tagText(textView: TextView, content: String) {
    textView.text = "# $content"
}

@BindingAdapter("writtenDateText")
fun writtenDateText(textView: TextView, date: String) {
    textView.text = date.replace('-', '.').substring(5)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("tagMeetingDateText")
fun tagMeetingDateText(textView: TextView, content: String) {
    textView.text = "# ${content.replace('-', '.').substring(2)}"
}

package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

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

@BindingAdapter("userImage")
fun userImage(imageView: ImageView, imageData: String?) {
    if(imageData != null) {
        Glide.with(imageView)
            .asBitmap()
            .load(imageData)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(imageView)
    }
}


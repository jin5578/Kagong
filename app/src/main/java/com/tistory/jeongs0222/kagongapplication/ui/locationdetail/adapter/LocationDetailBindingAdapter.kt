package com.tistory.jeongs0222.kagongapplication.ui.locationdetail.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.R


@BindingAdapter("locationDetailImage")
fun locationDetailImage(imageView: ImageView, uri: String?) {
    if(uri != null) {
        Glide.with(imageView)
            .load(uri)
            .apply(
                RequestOptions
                    .priorityOf(Priority.IMMEDIATE)
                    .placeholder(R.drawable.ic_error_outline_black_24dp)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .centerCrop()
            )
            .into(imageView)
    }
}
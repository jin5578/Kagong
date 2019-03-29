package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("userImage")
fun userImage(imageView: ImageView, uri: String?) {
    if(uri != null) {
        Glide.with(imageView)
            .asBitmap()
            .load(uri)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(imageView)
    }
}
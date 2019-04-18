package com.tistory.jeongs0222.kagongapplication.ui.howToUse.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("imageGlide")
fun imageGlide(imageView: ImageView, image: Int) {
    Glide.with(imageView)
        .asBitmap()
        .load(image)
        .into(imageView)
}
package com.tistory.jeongs0222.kagongapplication.ui.userprofile.adapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.R


@BindingAdapter("uUserProfile")
fun uUserProfile(imageView: ImageView, imageData: String?) {
    if(imageData != "") {
        Glide.with(imageView)
            .asBitmap()
            .load(imageData)
            .apply(RequestOptions
                .encodeFormatOf(Bitmap.CompressFormat.PNG)
                .placeholder(R.drawable.ic_error_outline_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .circleCrop()
            )
            .into(imageView)
    } else {
        Glide.with(imageView)
            .load(R.drawable.profileimage)
            .into(imageView)
    }
}
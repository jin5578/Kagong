package com.tistory.jeongs0222.kagongapplication.ui.userprofile.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.R
import java.text.SimpleDateFormat


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

@BindingAdapter("uUserSex")
fun uUserSex(textView: TextView, sex: String?) {
    if (sex != null) {
        textView.text = when (sex) {
            "female" -> "# " + "여성"

            else -> "# " + "남성"
        }
    }
}

@SuppressLint("SimpleDateFormat", "SetTextI18n")
@BindingAdapter("uUserAge")
fun uUserAge(textView: TextView, age: String?) {
    if(age != null) {
        val format1 = SimpleDateFormat("yyyy")

        val present = format1.format(System.currentTimeMillis())

        textView.text = "# " + (present.toInt() - age.toInt() + 1).toString() + "세"
    }
}
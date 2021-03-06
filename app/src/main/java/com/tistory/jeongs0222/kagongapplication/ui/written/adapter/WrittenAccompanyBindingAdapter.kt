package com.tistory.jeongs0222.kagongapplication.ui.written.adapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.model.host.writtenAccompany.WrittenAccompanyResult


@BindingAdapter("writtenAccompanyList")
fun writtenAccompanyList(recyclerView: RecyclerView, list: MutableList<WrittenAccompanyResult>?) {
    if(list != null) {
        (recyclerView.adapter as WrittenAccompanyAdapter).apply {
            submitList(list)
        }
    }
}

@BindingAdapter("writtenAccompanyProfile")
fun writtenAccompanyProfile(imageView: ImageView, uri: String) {
    if(uri != "") {
        Glide.with(imageView)
            .asBitmap()
            .load(uri)
            .apply(
                RequestOptions
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

package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.model.host.bringAccompany.BringAccompanyResult


@BindingAdapter("mAccompanyList")
fun mAccompanyList(recyclerView: RecyclerView, list: MutableList<BringAccompanyResult>?) {
    if(list != null) {
        (recyclerView.adapter as AccompanyAdapter).apply {
            submitList(list)

            //notifyDataSetChanged()
        }
    }
}

@BindingAdapter("mAccompanyNullVisibility")
fun mAccompanyNullVisibility(textView: TextView, list: MutableList<BringAccompanyResult>?) {
    if(list != null) {
        if(list.size == 0) {
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }
}

@BindingAdapter("mAccompanyProfile")
fun mAccompanyProfile(imageView: ImageView, uri: String) {
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
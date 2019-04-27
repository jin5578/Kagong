package com.tistory.jeongs0222.kagongapplication.ui.locationdetail.adapter

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.model.host.bringReview.BringLocationReviewResult


@BindingAdapter("locationReviewItem")
fun locationReviewItem(recyclerView: RecyclerView, item: MutableList<BringLocationReviewResult>?) {
    if (item != null) {
        (recyclerView.adapter as LocationReviewAdapter).apply {
            submitList(item)
        }
    }
}

@BindingAdapter("locationReviewProfile")
fun locationReviewProfile(imageView: ImageView, uri: String) {
    if (uri != "") {
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

@BindingAdapter("nullTextVisibility")
fun nullTextVisibility(textView: TextView, list: MutableList<BringLocationReviewResult>?) {
    if(list != null) {
        if(list.size == 0)
            textView.visibility = View.VISIBLE
        else
            textView.visibility = View.GONE
    }
}

@BindingAdapter("seeMoreVisibility")
fun seeMoreVisibility(textView: TextView, list: MutableList<BringLocationReviewResult>?) {
    if(list != null) {
        if(list.size != 0)
            textView.visibility = View.VISIBLE
        else
            textView.visibility = View.GONE
    }
}

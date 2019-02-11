package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.model.host.areasearch.AreaSearchResult


@BindingAdapter("addSearchList")
fun searchList(recyclerView: RecyclerView, list: MutableList<AreaSearchResult>?) {
    if(list != null)
        (recyclerView.adapter as AreaSearchHistoryAdapter).apply {
            submitList(list)
        }
}

@BindingAdapter("searchImageUrl")
fun setImageUrl(view: ImageView, url: String?) {
    if(url != null) {
        Glide
            .with(view)
            .asBitmap()
            .load(url)
            .apply( RequestOptions.bitmapTransform(CircleCrop()))
            .into(view)
    }
}
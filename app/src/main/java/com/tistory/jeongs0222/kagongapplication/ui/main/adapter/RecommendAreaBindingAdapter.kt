package com.tistory.jeongs0222.kagongapplication.ui.view.main.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.model.host.recommendArea.RecommendAreaResult


@BindingAdapter("addRecommendList")
fun recommendList(recyclerView: RecyclerView, list: MutableList<RecommendAreaResult>?) {
    if(list != null)
        (recyclerView.adapter as RecommendAreaAdapter).apply {
            submitList(list)
        }
}

@BindingAdapter("recommendImageUrl")
fun setRecommentImageUrl(view: ImageView, url: String?) {
    if(url != null) {
        Glide.with(view)
            .asBitmap()
            .load(url)
            .apply(RequestOptions().centerCrop())
            .into(view)
    }
}
package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.model.host.bringAreaList.BringAreaListResult


@BindingAdapter("accompanyAreaList")
fun accompanyAreaList(recyclerView: RecyclerView, list: MutableList<BringAreaListResult>?) {
    if(list != null)
        (recyclerView.adapter as BringAreaListAdapter).apply {
            submitList(list)
        }
}

@BindingAdapter("areaListImageUrl")
fun areaListImageUrl(view: ImageView, url: String?) {
    if(url != null) {
        Glide
            .with(view)
            .asBitmap()
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(view)
    }
}

@BindingAdapter("areaRecyclerVisibility")
fun areaRecyclerVisibility(recyclerView: RecyclerView, bool: Boolean?) {
    if(bool != null) {
        when(bool) {
            true -> recyclerView.visibility = View.VISIBLE

            false -> recyclerView.visibility = View.GONE
        }
    }
}
package com.tistory.jeongs0222.kagongapplication.ui.areadetail.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tistory.jeongs0222.kagongapplication.model.host.areaInformation.AreaInformationResult


@BindingAdapter("addInformationList")
fun informationList(recyclerView: RecyclerView, list: MutableList<AreaInformationResult>?) {
    if(list != null) {
        (recyclerView.adapter as AreaInformationAdapter).apply {
            submitList(list)
        }
    }
}

@BindingAdapter("imageIntro")
fun imageIntro(imageView: ImageView, uri: String?) {
    if(uri != null) {
        Glide.with(imageView)
            .asBitmap()
            .load(uri)
            .into(imageView)
    }
}

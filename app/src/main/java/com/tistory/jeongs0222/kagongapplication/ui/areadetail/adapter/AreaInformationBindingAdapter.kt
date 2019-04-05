package com.tistory.jeongs0222.kagongapplication.ui.areadetail.adapter

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.R
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
            .apply(
                RequestOptions
                    .encodeFormatOf(Bitmap.CompressFormat.PNG)
                    .placeholder(R.drawable.ic_error_outline_black_24dp)
                    .error(R.drawable.ic_error_outline_black_24dp)
            )
            .into(imageView)
    }
}

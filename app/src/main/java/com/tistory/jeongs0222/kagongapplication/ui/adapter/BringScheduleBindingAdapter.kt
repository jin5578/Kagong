package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.model.host.bringSchedule.BringScheduleResult


@BindingAdapter("myScheduleList")
fun bringScheduleList(recyclerView: RecyclerView, list: MutableList<BringScheduleResult>?) {
    if (list != null) {
        (recyclerView.adapter as BringScheduleAdapter).apply {
            submitList(list)

            notifyDataSetChanged()
        }
    }
}

@BindingAdapter("imageUrl")
fun setImage(view: ImageView, url: String?) {
    if(url != null) {
        Glide.with(view)
            .asBitmap()
            .load(url)
            .apply(RequestOptions().centerCrop())
            .into(view)
    }
}

@BindingAdapter("date1Text")
fun date1Text(textView: TextView, date: String?) {
    if (date != null) {
        textView.text = date.substring(0, 10)
    }
}


@BindingAdapter("date2Text")
fun date2Text(textView: TextView, date: String?) {
    if (date != null) {
        textView.text = date.substring(13)
    }
}
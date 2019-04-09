package com.tistory.jeongs0222.kagongapplication.ui.main.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.R
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

@BindingAdapter("itemNullVisibility")
fun itemNullVisibility(textView: TextView, list: MutableList<BringScheduleResult>?) {
    if(list!!.size == 0) {
        textView.visibility = View.VISIBLE
    } else {
        textView.visibility = View.GONE
    }
}

@BindingAdapter("imageUrl")
fun setImage(view: ImageView, url: String?) {
    if(url != null) {
        Glide.with(view)
            .asBitmap()
            .load(url)
            .apply(RequestOptions
                .encodeFormatOf(Bitmap.CompressFormat.PNG)
                .placeholder(R.drawable.ic_error_outline_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .centerCrop()
            )
            .into(view)

        view.setColorFilter(Color.parseColor("#C3C3C3"), PorterDuff.Mode.MULTIPLY)
    }
}

@BindingAdapter("date1Text")
fun date1Text(textView: TextView, date: String?) {
    if (date != null) {
        textView.text = date.replace("-", ".")
    }
}

@BindingAdapter("typeDivider")
fun typeDivider(textView: TextView, type: String?) {
    if(type != null) {
        when(type) {
            "past" -> textView.text = "지난 여정"
            "future" -> textView.text = "다가오는 여정"
            "present" ->textView.text = "여행중"
        }
    }
}

@BindingAdapter("areaConverter")
fun areaConverter(textView: TextView, area: String?) {
    if(area != null) {
        when(area) {
            "런던" -> textView.text = "LONDON"
            "마드리드" -> textView.text = "MADRID"
            "바르셀로나" -> textView.text = "BARCELONA"
            "파리" -> textView.text = "PARIS"
        }
    }
}
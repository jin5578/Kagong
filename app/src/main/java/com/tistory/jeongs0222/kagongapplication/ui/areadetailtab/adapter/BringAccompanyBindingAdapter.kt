package com.tistory.jeongs0222.kagongapplication.ui.areadetailtab.adapter

import android.annotation.SuppressLint
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
import java.text.SimpleDateFormat


@BindingAdapter("accompanyList")
fun accompanyList(recyclerView: RecyclerView, list: MutableList<BringAccompanyResult>?) {
    if(list != null) {
        (recyclerView.adapter as BringAccompanyAdapter).apply {
            submitList(list)
        }
    }
}

@BindingAdapter("accompanyNullVisibility")
fun accompanyNullVisibility(textView: TextView, list: MutableList<BringAccompanyResult>?) {
    if(list != null) {
        if(list.size == 0) {
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }
}

@BindingAdapter("accompanyProfile")
fun accompanyProfile(imageView: ImageView, uri: String) {
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

@BindingAdapter("accompanySex")
fun accompanySex(textView: TextView, sex: String?) {
    if (sex != null) {
        textView.text = when (sex) {
            "female" -> "# " + "여성"

            else -> "# " + "남성"
        }
    }
}

@SuppressLint("SimpleDateFormat", "SetTextI18n")
@BindingAdapter("accompanyAge")
fun accompanyAge(textView: TextView, age: String?) {
    if(age != null) {
        val format1 = SimpleDateFormat("yyyy")

        val present = format1.format(System.currentTimeMillis())

        textView.text = "# " + (present.toInt() - age.toInt() + 1).toString() + "세"
    }
}

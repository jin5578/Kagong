package com.tistory.jeongs0222.kagongapplication.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tistory.jeongs0222.kagongapplication.R


@BindingAdapter("agreementUrl")
fun setUrl(view: ImageView, agreement: Boolean) {
    if(agreement)
        Glide.with(view).load(R.drawable.ic_check_pink_24dp).into(view)
    else
        Glide.with(view).load(R.drawable.ic_check_black_24dp).into(view)
}

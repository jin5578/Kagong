package com.tistory.jeongs0222.kagongapplication.ui.googleMap.adapter

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tistory.jeongs0222.kagongapplication.R


@BindingAdapter("locationImage")
fun locationImage(imageView: ImageView, uri: String?) {
    if (uri != null) {
        Glide.with(imageView)
            .asBitmap()
            .load(uri)
            .apply(
                RequestOptions()
                    .encodeFormat(Bitmap.CompressFormat.PNG)
                    .placeholder(R.drawable.ic_error_outline_black_24dp)
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .centerCrop()
            )
            .into(imageView)
    }
}

@BindingAdapter("infoVisibility")
fun infoVisibility(constraintLayout: ConstraintLayout, bool: Boolean?) {
    if (bool != null) {
        if (bool == true)
            constraintLayout.visibility = View.VISIBLE
        else
            constraintLayout.visibility = View.GONE
    }

}
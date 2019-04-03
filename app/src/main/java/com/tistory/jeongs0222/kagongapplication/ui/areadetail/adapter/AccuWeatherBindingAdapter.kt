package com.tistory.jeongs0222.kagongapplication.ui.areadetail.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.model.accuweather.AccuWeatherResponse2


@BindingAdapter("accuWeatherList")
fun accuWeatherList(recyclerView: RecyclerView, list: MutableList<AccuWeatherResponse2>?) {
    if(list != null) {
        (recyclerView.adapter as AccuWeatherAdapter).apply {
            submitList(list)
        }
    }
}

@BindingAdapter("temperature")
fun temperature(textView: TextView, temperature: Double) {
    /*textView.text = ((5.0/9)*(temperature-32.0)).toInt().toString()*/
    textView.text = (Math.round(((5.0/9)*(temperature-32.0))).toString()+"°")
}

@BindingAdapter("accuWeatherItem")
fun accuWeatherItem(imageView: ImageView, imageIcon: Int?) {
    if (imageIcon != null) {
        Glide.with(imageView)
            .load(imageDivider(imageIcon))
            .into(imageView)
    }
}

private fun imageDivider(imageIcon: Int): Int {
    return when (imageIcon) {
        in 1..4 -> R.drawable.sunny
        in 5..6 -> R.drawable.mostly_cloudy
        in 7..11 -> R.drawable.cloudy
        in 12..14 -> R.drawable.mostly_rain
        15 -> R.drawable.storm
        in 16..17 -> R.drawable.mostly_rain
        18 -> R.drawable.raining
        19 -> R.drawable.cloudy
        22 -> R.drawable.snowing
        in 23..29 -> R.drawable.mostly_snowing
        30 -> R.drawable.hot
        31 -> R.drawable.cold
        32 -> R.drawable.windy
        in 33..34 -> R.drawable.clear_night
        in 35..38 -> R.drawable.cloudy_night
        in 39..40 -> R.drawable.rainy_night
        in 41..42 -> R.drawable.storm_night
        43 -> R.drawable.cloudy_night
        else -> R.drawable.snow_night
    }
}



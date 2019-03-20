package com.tistory.jeongs0222.kagongapplication.ui.areadetail.adapter

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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

@BindingAdapter("accuWeatherVisibility")
fun accuWeatherVisibility(constraintLayout: ConstraintLayout, sort: Boolean) {
    if (sort) {
        constraintLayout.visibility = View.VISIBLE
    } else {
        constraintLayout.visibility = View.GONE
    }
}

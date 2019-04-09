/*
package com.tistory.jeongs0222.kagongapplication.ui.areadetail.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.kagongapplication.databinding.ItemAccuweatherBinding
import com.tistory.jeongs0222.kagongapplication.model.accuweather.AccuWeatherResponse2


class AccuWeatherAdapter(
    private val lifecycleOwner: LifecycleOwner
): ListAdapter<AccuWeatherResponse2, AccuWeatherAdapter.ViewHolder>(WeatherDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAccuweatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position).Date, getItem(position))

        Log.e("weather icon", getItem(position).Day.Icon.toString())
    }

    class ViewHolder(
        private val binding: ItemAccuweatherBinding,
        private val lifecycleOwner: LifecycleOwner
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(date: String, w: AccuWeatherResponse2) {
            binding.date.text = date.substring(5, 10)

            binding.weatherItem = w

            binding.lifecycleOwner = lifecycleOwner
            binding.executePendingBindings()
        }
    }

    object WeatherDiff: DiffUtil.ItemCallback<AccuWeatherResponse2>() {
        override fun areItemsTheSame(
            oldItem: AccuWeatherResponse2,
            newItem: AccuWeatherResponse2
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AccuWeatherResponse2,
            newItem: AccuWeatherResponse2
        ): Boolean {
            return oldItem == newItem
        }
    }
}*/

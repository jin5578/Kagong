package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.tistory.jeongs0222.kagongapplication.R


interface DynamicProvider {
    fun location(initView: ConstraintLayout, location: List<String>)
}

class DynamicProviderImpl(
    private val activity: Activity
) : DynamicProvider {

    override fun location(initView: ConstraintLayout, location: List<String>) {

        var temp = 1
        var lasttemp = 1

        for (i in 0 until location.size) {
            if(i % 2 == 0) {
                val constraintSet = ConstraintSet()

                val locationView = activity.layoutInflater.inflate(R.layout.layout_detail_location, null)
                locationView.id = temp

                val params = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )

                locationView.layoutParams = params

                locationView.findViewById<TextView>(R.id.location).text = location[i]

                initView.addView(locationView)

                constraintSet.clone(initView)

                if (temp == 1) {
                    constraintSet.connect(temp, ConstraintSet.TOP, R.id.day, ConstraintSet.BOTTOM, 80)
                    constraintSet.connect(temp, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
                    constraintSet.connect(temp, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
                } else {
                    constraintSet.connect(temp, ConstraintSet.TOP, lasttemp, ConstraintSet.BOTTOM, 0)
                    constraintSet.connect(temp, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
                    constraintSet.connect(temp, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
                }

                constraintSet.applyTo(initView)

                lasttemp = temp
                temp++
            } else {

                Log.e("test", location[i])
                val constraintSet = ConstraintSet()

                val wayView = activity.layoutInflater.inflate(R.layout.layout_detail_way, null)
                wayView.id = temp

                val params = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )

                wayView.layoutParams = params

                wayView.findViewById<ImageView>(R.id.way).setImageResource(imageDivider(location[i]))

                initView.addView(wayView)

                constraintSet.clone(initView)

                constraintSet.connect(temp, ConstraintSet.TOP, lasttemp, ConstraintSet.BOTTOM, 0)
                constraintSet.connect(temp, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
                constraintSet.connect(temp, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)

                constraintSet.applyTo(initView)

                lasttemp = temp
                temp++
            }

        }
    }

    private fun imageDivider(way: String): Int {
        return when(way) {
            "walk" -> R.drawable.ic_directions_walk_gray_24dp

            "run" -> R.drawable.ic_directions_run_gray_24dp

            "bike" -> R.drawable.ic_directions_bike_gray_24dp

            "car" -> R.drawable.ic_directions_car_gray_24dp

            "taxi" -> R.drawable.ic_local_taxi_gray_24dp

            "bus" -> R.drawable.ic_directions_bus_gray_24dp

            "subway" -> R.drawable.ic_directions_subway_gray_24dp

            "train" -> R.drawable.ic_directions_train_gray_24dp

            else -> R.drawable.ic_directions_boat_gray_24dp
        }
    }
}
package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
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
        }
    }
}
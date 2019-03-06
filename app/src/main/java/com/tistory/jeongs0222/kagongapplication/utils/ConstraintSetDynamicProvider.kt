package com.tistory.jeongs0222.kagongapplication.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.FragmentActivity
import com.tistory.jeongs0222.kagongapplication.R


interface ConstraintSetDynamicProvider {

    fun createDeparture(initView: ConstraintLayout)

    fun createWayAndArrival(initView: ConstraintLayout)

    fun deleteWayAndArrival(initView: ConstraintLayout)

}

class ConstraintSetDynamicProviderImpl(
    private val activity: FragmentActivity
): ConstraintSetDynamicProvider {
    private var temp = 1
    private var lasttemp = 1

    private val params = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
    )


    override fun createDeparture(initView: ConstraintLayout) {

        val constraintSet = ConstraintSet()

        val departureView = activity.layoutInflater.inflate(R.layout.layout_departure, null)
        departureView.id = temp
        departureView.layoutParams = params

        initView.addView(departureView)

        constraintSet.clone(initView)

        constraintSet.connect(temp, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        constraintSet.connect(temp, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
        constraintSet.connect(temp, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)

        constraintSet.applyTo(initView)

        lasttemp = temp

        temp++
    }

    override fun createWayAndArrival(initView: ConstraintLayout) {

        val constraintSet = ConstraintSet()

        val wayView = activity.layoutInflater.inflate(R.layout.layout_way_and_arrival, null)
        wayView.id = temp
        wayView.layoutParams = params

        initView.addView(wayView)

        constraintSet.clone(initView)

        constraintSet.connect(temp, ConstraintSet.TOP, lasttemp, ConstraintSet.BOTTOM, 0)
        constraintSet.connect(temp, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
        constraintSet.connect(temp, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)

        constraintSet.applyTo(initView)

        lasttemp = temp

        temp++
    }

    override fun deleteWayAndArrival(initView: ConstraintLayout) {
        if(temp > 3) {
            initView.removeView(initView.getViewById(lasttemp))

            lasttemp--
            temp--
        }
    }
}
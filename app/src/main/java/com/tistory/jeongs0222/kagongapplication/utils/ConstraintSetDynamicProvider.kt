package com.tistory.jeongs0222.kagongapplication.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddLocationViewModel
import kotlinx.android.synthetic.main.layout_departure.view.*
import kotlinx.android.synthetic.main.layout_way_and_arrival.view.*


interface ConstraintSetDynamicProvider {

    fun createDeparture(initView: ConstraintLayout)

    fun createWayAndArrival(initView: ConstraintLayout)

    fun deleteWayAndArrival(initView: ConstraintLayout)

    fun dynamicColorFilter(wayView: View, id: Int)
}

class ConstraintSetDynamicProviderImpl(
    private val activity: FragmentActivity,
    private val addLocationViewModel: AddLocationViewModel
): ConstraintSetDynamicProvider {
    private var temp = 1
    private var lasttemp = 1

    private var imageList = mutableListOf(R.id.walk, R.id.run, R.id.bike, R.id.car, R.id.taxi, R.id.bus)
    private var imageString = mutableListOf("walk", "run", "bike", "car", "taxi", "bus")

    override fun createDeparture(initView: ConstraintLayout) {

        val constraintSet = ConstraintSet()

        val departureView = activity.layoutInflater.inflate(R.layout.layout_departure, null)
        departureView.id = temp

        Log.e("departureViewId", departureView.id.toString())

        val params = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        departureView.layoutParams = params

        initView.addView(departureView)

        departureView.departure.setOnClickListener {
            addLocationViewModel.searchLocationClickEvent(departureView.id)

        }

        constraintSet.clone(initView)

        constraintSet.connect(temp, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        constraintSet.connect(temp, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
        constraintSet.connect(temp, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)

        constraintSet.applyTo(initView)

        lasttemp = temp

        temp++
    }

    override fun createWayAndArrival(initView: ConstraintLayout) {

        if(lasttemp < 15) {
            val constraintSet = ConstraintSet()

            val wayView = activity.layoutInflater.inflate(R.layout.layout_way_and_arrival, null)
            wayView.id = temp

            Log.e("wayViewId", wayView.id.toString())


            val params = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            wayView.layoutParams = params

            initView.addView(wayView)

            wayView.arrival.setOnClickListener {
                addLocationViewModel.searchLocationClickEvent(wayView.id)
            }

            for(i in 0 until imageList.size) {
                wayView.findViewById<ImageView>(imageList[i]).setOnClickListener {
                    Log.e("id", it.id.toString())

                    dynamicColorFilter(wayView, it.id)
                }
            }

            constraintSet.clone(initView)

            constraintSet.connect(temp, ConstraintSet.TOP, lasttemp, ConstraintSet.BOTTOM, 0)
            constraintSet.connect(temp, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
            constraintSet.connect(temp, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)

            constraintSet.applyTo(initView)

            lasttemp = temp

            temp++
        }
    }

    override fun deleteWayAndArrival(initView: ConstraintLayout) {
        if(temp > 3) {
            initView.removeView(initView.getViewById(lasttemp))

            lasttemp--
            temp--
        }
    }

    override fun dynamicColorFilter(wayView: View, id: Int) {
        for(i in 0 until imageList.size) {
            if(imageList[i] == id) {
                wayView.findViewById<ImageView>(imageList[i]).setColorFilter(ContextCompat.getColor(activity.applicationContext, R.color.colorRed), android.graphics.PorterDuff.Mode.MULTIPLY)
                wayView.findViewById<ImageView>(imageList[i]).tag = 1
            } else {
                wayView.findViewById<ImageView>(imageList[i]).setColorFilter(ContextCompat.getColor(activity.applicationContext, R.color.colorGray5), android.graphics.PorterDuff.Mode.MULTIPLY)
                wayView.findViewById<ImageView>(imageList[i]).tag = 2
            }
        }
    }
}
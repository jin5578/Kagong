package com.tistory.jeongs0222.kagongapplication.ui.view.activity

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.kagongapplication.R
import com.tistory.jeongs0222.kagongapplication.databinding.ActivityAddDetailScheduleBinding
import com.tistory.jeongs0222.kagongapplication.ui.viewmodel.AddDetailScheduleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddDetailScheduleActivity : BaseActivity<ActivityAddDetailScheduleBinding>() {

    private val TAG = "AddDetailScheduleActivity"

    override val layoutResourceId: Int = R.layout.activity_add_detail_schedule

    private val addDetailScheduleViewModel by viewModel<AddDetailScheduleViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addDetailScheduleViewModel.previousClick.observe(this@AddDetailScheduleActivity, Observer {
            finish()
        })

        addDetailScheduleViewModel.moreVisibility.observe(this@AddDetailScheduleActivity, Observer {
            if(it) {
                moreExpandAnimation()
            } else {
                moreContractAnimation()
            }
        })



        viewDataBinding.adViewModel = addDetailScheduleViewModel
        viewDataBinding.lifecycleOwner = this@AddDetailScheduleActivity
    }

    private fun moreExpandAnimation() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.layout_more_expand)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 700

        TransitionManager.beginDelayedTransition(viewDataBinding.includeMore.moreLayout, transition)

        constraintSet.applyTo(viewDataBinding.includeMore.moreLayout)
    }

    private fun moreContractAnimation() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.layout_more_contract)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 700

        TransitionManager.beginDelayedTransition(viewDataBinding.includeMore.moreLayout, transition)

        constraintSet.applyTo(viewDataBinding.includeMore.moreLayout)
    }

    override fun onBackPressed() {
        finish()
    }
}
